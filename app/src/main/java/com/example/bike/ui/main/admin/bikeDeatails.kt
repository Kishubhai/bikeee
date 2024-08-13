package com.example.bike.ui.main.admin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bike.R
import com.example.bike.data.model.Bike
import com.example.bike.ui.main.admin.viewmodel.BikeViewModel
import com.google.android.material.snackbar.Snackbar

class bikeDeatails : AppCompatActivity() {

    private val bikeViewModel: BikeViewModel by viewModels()

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_deatails)

        val bikeNameField = findViewById<EditText>(R.id.bikeName)
        val companyNameField = findViewById<EditText>(R.id.companyName)
        val vehicleNumberField = findViewById<EditText>(R.id.phoneNumber)
        val chessisNumberField = findViewById<EditText>(R.id.chessisNumber)
        val manufacturingYearField = findViewById<EditText>(R.id.manYear)
        val uploadButton = findViewById<Button>(R.id.btnLogin)

        uploadButton.setOnClickListener {
            val bikeName = bikeNameField.text.toString()
            val companyName = companyNameField.text.toString()
            val vehicleNumber = vehicleNumberField.text.toString()
            val chessisNumber = chessisNumberField.text.toString()
            val manufacturingYear = manufacturingYearField.text.toString()

            // Assuming you have a user ID (e.g., from authentication)
            val userId = "user123" // Replace with actual user ID

            val bike = Bike(bikeName, companyName, vehicleNumber, chessisNumber, manufacturingYear)

            bikeViewModel.uploadBikeData(
                userId,
                bike,
                onSuccess = {
                    // Show a success Snackbar
                    Snackbar.make(it, "Bike details uploaded successfully", Snackbar.LENGTH_LONG).show()
                    // Hide the keyboard
                    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
                    // Close the activity
                    finish()
                },
                onFailure = { exception ->
                    // Show an error Snackbar
                    Snackbar.make(it, "Failed to upload bike details: ${exception.message}", Snackbar.LENGTH_LONG).show()
                }
            )
        }
    }
}
