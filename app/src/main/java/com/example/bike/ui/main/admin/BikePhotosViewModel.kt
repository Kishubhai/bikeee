package com.example.bike.ui.main.admin

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.app.data.repository.ImageRepository

class BikePhotosViewModel(private val repository: ImageRepository) : ViewModel() {

    val images: LiveData<List<Uri>> = repository.getImages()

    fun addImage(uri: Uri) {
        repository.addImage(uri)
    }
}
