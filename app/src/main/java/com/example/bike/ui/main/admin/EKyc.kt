// File: EKyc.kt
package com.example.bike.ui.main.admin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bike.R
import com.example.bike.data.model.EKycData
import com.example.bike.ui.main.admin.viewmodel.EKycViewModel
import com.google.android.material.snackbar.Snackbar
import android.view.inputmethod.InputMethodManager
import android.content.Context

class EKyc : AppCompatActivity() {

    private val eKycViewModel: EKycViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekyc)

        val realNameField = findViewById<EditText>(R.id.realName)
        val fatherNameField = findViewById<EditText>(R.id.fatherName)
        val addressField = findViewById<EditText>(R.id.address)
        val adharNumberField = findViewById<EditText>(R.id.addharNumber)
        val verifyButton = findViewById<Button>(R.id.btnLogin)

        verifyButton.setOnClickListener {
            val realName = realNameField.text.toString()
            val fatherName = fatherNameField.text.toString()
            val address = addressField.text.toString()
            val adharNumber = adharNumberField.text.toString()

            val eKycData = EKycData(realName, fatherName, address, adharNumber)

            eKycViewModel.uploadEKycData(
                eKycData,
                onSuccess = {
                    // Show a success Snackbar
                    Snackbar.make(it, "eKYC data uploaded successfully", Snackbar.LENGTH_LONG).show()
                    // Close the activity
                    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)

                    finish()
                },
                onFailure = { exception ->
                    // Show an error Snackbar
                    Snackbar.make(it, "Failed to upload data: ${exception.message}", Snackbar.LENGTH_LONG).show()
                }
            )
        }

    }
}
