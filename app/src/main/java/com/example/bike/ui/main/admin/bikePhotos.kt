package com.example.bike.ui.main.admin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.app.data.repository.ImageRepository
import com.example.bike.R
import com.example.bike.databinding.ActivityBikePhotosBinding
import com.example.bike.ui.adapter.ImageSliderAdapter

class bikePhotos : AppCompatActivity() {

    private lateinit var binding:ActivityBikePhotosBinding

    private lateinit var viewModel: BikePhotosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_photos)

        binding = DataBindingUtil.setContentView<ActivityBikePhotosBinding>(this, R.layout.activity_bike_photos)
        binding.lifecycleOwner = this

        // Initialize ViewModel
        val repository = ImageRepository()
        val factory = BikePhotosViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(BikePhotosViewModel::class.java)
        binding.viewModel = viewModel

        val adapter = ImageSliderAdapter(viewModel.images.value ?: emptyList())
        binding.imageSlider.adapter = adapter

        viewModel.images.observe(this) { images ->
            adapter.notifyDataSetChanged()
        }

        binding.pickImageButton.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                viewModel.addImage(uri)
            }
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}
