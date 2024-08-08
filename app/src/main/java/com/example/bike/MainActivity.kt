package com.example.bike

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bike.ui.main.admin.homeAdmin
import com.example.bike.ui.main.admin.personDetails

class MainActivity : AppCompatActivity() {

    private lateinit var adminButton:ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adminButton = findViewById(R.id.adminButton)

        adminButton.setOnClickListener {
            val intent1 = Intent(this, homeAdmin::class.java)
            startActivity(intent1)}
    }
}