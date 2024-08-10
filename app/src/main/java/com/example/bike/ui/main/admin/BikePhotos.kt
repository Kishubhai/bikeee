package com.example.bike.ui.main.admin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Constants.KEY_PERMISSION_GRANTED
import com.example.Constants.PREFS_NAME
import com.example.Constants.REQUEST_CODE_PICK_IMAGE
import com.example.Extensions.checkAndRequestImagePermission
import com.example.Extensions.handlePermissionResult
import com.example.bike.R
import com.example.bike.databinding.ActivityBikePhotosBinding

class BikePhotos : AppCompatActivity() {
    private lateinit var binding: ActivityBikePhotosBinding
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
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                // Handle the selected image URI here
            }
        }
    }
}
