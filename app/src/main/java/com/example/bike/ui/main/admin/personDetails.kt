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
import com.example.bike.data.model.Person
import com.example.bike.ui.main.admin.viewmodel.PersonViewModel
import com.google.android.material.snackbar.Snackbar

class personDetails : AppCompatActivity() {

    private val personViewModel: PersonViewModel by viewModels()

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        val nameField = findViewById<EditText>(R.id.name)
        val phoneField = findViewById<EditText>(R.id.phoneNumber)
        val emailField = findViewById<EditText>(R.id.email)
        val altPhoneField = findViewById<EditText>(R.id.altPhoneNumber)
        val uploadButton = findViewById<Button>(R.id.btnLogin)

        uploadButton.setOnClickListener {
            val name = nameField.text.toString()
            val phoneNumber = phoneField.text.toString()
            val email = emailField.text.toString()
            val altPhoneNumber = altPhoneField.text.toString()

            val person = Person(name, phoneNumber, email, altPhoneNumber)

            personViewModel.uploadPersonData(
                person,
                onSuccess = {
                    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)

                    // Show a success Snackbar
                    Snackbar.make(it, "Data uploaded successfully", Snackbar.LENGTH_LONG).show()
                    // Close the activity
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
