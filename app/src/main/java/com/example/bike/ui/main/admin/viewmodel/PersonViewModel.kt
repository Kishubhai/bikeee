package com.example.bike.ui.main.admin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bike.data.model.Person
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PersonViewModel : ViewModel() {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("persons")

    fun uploadPersonData(person: Person, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val userKey = person.email.replace(".", "_")

        // Upload or update person data in Firebase under the unique key
        database.child(userKey).setValue(person)
            .addOnSuccessListener {
                // Call onSuccess callback if the upload is successful
                onSuccess()
            }
            .addOnFailureListener { exception ->
                // Call onFailure callback if the upload fails
                onFailure(exception)
            }
    }
}
