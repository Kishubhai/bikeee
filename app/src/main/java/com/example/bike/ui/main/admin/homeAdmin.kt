package com.example.bike.ui.main.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.bike.R

class homeAdmin : AppCompatActivity() {
    private lateinit var persondetails:CardView
    private lateinit var eKycc:CardView
    private lateinit var bikeDetails:CardView
    private lateinit var bikePhotoss:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_admin)

        persondetails = findViewById(R.id.addDetails)
        eKycc = findViewById(R.id.kyc)
        bikeDetails = findViewById(R.id.addBikeDetails)
        bikePhotoss = findViewById(R.id.addBikeImage)

        persondetails.setOnClickListener {
        val intent1 = Intent(this, personDetails::class.java)
        startActivity(intent1)}

        eKycc.setOnClickListener {
        val intent2 = Intent(this, EKyc::class.java)
        startActivity(intent2)}

        bikeDetails.setOnClickListener {
        val intent3 = Intent(this, bikeDeatails::class.java)
        startActivity(intent3)}

        bikePhotoss.setOnClickListener {
        val intent4 = Intent(this, BikePhotos::class.java)
        startActivity(intent4)}


    }
}