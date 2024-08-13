package com.example.bike.data.repository

import com.example.bike.data.model.Person
import com.google.firebase.database.FirebaseDatabase

class PersonRepository {

    private val database = FirebaseDatabase.getInstance().reference

    fun uploadPerson(person: Person, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val personId = database.push().key
        personId?.let {
            database.child("persons").child(it).setValue(person)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { exception ->
                    onFailure(exception)
                }
        } ?: onFailure(Exception("Person ID is null"))
    }
}
