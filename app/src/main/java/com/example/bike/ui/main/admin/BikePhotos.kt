package com.example.bike.ui.main.admin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import androidx.viewpager2.widget.ViewPager2
import com.example.Constants.KEY_PERMISSION_GRANTED
import com.example.Constants.PREFS_NAME
import com.example.Constants.REQUEST_CODE_PICK_IMAGE
import com.example.Extensions.checkAndRequestImagePermission
import com.example.Extensions.handlePermissionResult
import com.example.bike.R
import com.example.bike.databinding.ActivityBikePhotosBinding
import com.example.bike.ui.adapter.BikeImagePagerAdapter

class BikePhotos : AppCompatActivity() {
    private lateinit var binding: ActivityBikePhotosBinding
    private lateinit var imagePagerAdapter: BikeImagePagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBikePhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pickImageButton.setOnClickListener{
            val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val permissionGranted = sharedPreferences.getBoolean(KEY_PERMISSION_GRANTED, false)
            if(permissionGranted)
            {
                openGallery()
               // Log.d("Bikee from if", "Permission granted: $permissionGranted")
            }
            else{
                Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"  // MIME type for images
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)  // Allow multiple selection
        }
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val images = mutableListOf<Uri>()

            // Check if multiple images are selected
            if (data?.clipData != null) {
                val clipData = data.clipData
                for (i in 0 until clipData!!.itemCount) {
                    val uri = clipData.getItemAt(i).uri
                    images.add(uri)
                }
            } else {
                // Single image selected
                data?.data?.let { uri ->
                    images.add(uri)
                }
            }
            imagePagerAdapter = BikeImagePagerAdapter(images)
            binding.selectImages.adapter = imagePagerAdapter
            if(images.size >= 2)
            setupDotsIndicator(images.size)
            binding.selectImages.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    updateDotsIndicator(position)
                }
            })
        }
    }

    private fun setupDotsIndicator(count: Int) {
        binding.dots.removeAllViews()
        val dots = Array(count) { ImageView(this) }
        for (i in dots.indices) {
            dots[i] = ImageView(this).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@BikePhotos,
                        if (i == 0) R.drawable.active_dot else R.drawable.inactive_dot
                    )
                )
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(8, 0, 8, 0)
                }
                binding.dots.addView(this, params)
            }
        }
    }

    private fun updateDotsIndicator(position: Int) {
        val childCount = binding.dots.childCount
        for (i in 0 until childCount) {
            val imageView = binding.dots.getChildAt(i) as ImageView
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (i == position) R.drawable.active_dot else R.drawable.inactive_dot
                )
            )
        }
    }

}
