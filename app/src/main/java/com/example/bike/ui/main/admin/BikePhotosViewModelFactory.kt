package com.example.bike.ui.main.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.data.repository.ImageRepository

class BikePhotosViewModelFactory(private val repository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BikePhotosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BikePhotosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
