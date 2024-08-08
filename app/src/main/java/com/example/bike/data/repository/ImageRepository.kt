package com.example.app.data.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ImageRepository {
    private val images = MutableLiveData<List<Uri>>(emptyList())

    fun getImages(): LiveData<List<Uri>> = images

    fun addImage(uri: Uri) {
        val updatedImages = images.value?.toMutableList() ?: mutableListOf()
        updatedImages.add(uri)
        images.value = updatedImages
    }
}
