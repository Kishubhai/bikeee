package com.example.bike.ui.main.admin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bike.data.model.Person
import com.example.bike.data.repository.PersonRepository

class PersonViewModel(private val repository: PersonRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val altPhoneNumber = MutableLiveData<String>()

    private val _uploadStatus = MutableLiveData<Boolean>()
    val uploadStatus: LiveData<Boolean> = _uploadStatus

    fun uploadPerson() {
        val person = Person(
            name = name.value ?: "",
            phoneNumber = phoneNumber.value ?: "",
            email = email.value ?: "",
            altPhoneNumber = altPhoneNumber.value ?: ""
        )

        repository.uploadPerson(person,
            onSuccess = {
                _uploadStatus.value = true
            },
            onFailure = {
                _uploadStatus.value = false
            })
    }
}
