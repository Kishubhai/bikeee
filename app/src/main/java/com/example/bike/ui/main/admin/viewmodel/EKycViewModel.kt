package com.example.bike.ui.main.admin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bike.data.model.EKycData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EKycViewModel : ViewModel() {

    // Reference to Firebase Realtime Database
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("ekycData")

    // Function to upload eKYC data to Firebase
    fun uploadEKycData(
        eKycData: EKycData,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Create a unique ID for the eKYC entry
        val eKycId = database.push().key ?: return
//        val userKey = EKycData.re

        // Upload eKYC data to Firebase
        database.child(eKycId).setValue(eKycData)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
}
