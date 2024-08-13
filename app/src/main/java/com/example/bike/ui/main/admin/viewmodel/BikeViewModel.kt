package com.example.bike.ui.main.admin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bike.data.model.Bike
import com.example.bike.data.model.Person
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BikeViewModel : ViewModel() {

    // Reference to Firebase Realtime Database
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("BikeDetails")
//    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("persons")

    // Function to upload bike data to Firebase
    fun uploadBikeData(userId: String, bike: Bike, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val bikeDetailsRef = database.child(userId).child("bikeDetails")
//        val userKey = Person.email.replace(".", "_")

        bikeDetailsRef.setValue(bike)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
}
