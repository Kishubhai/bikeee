package com.example.bike

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.Extensions.checkAndRequestImagePermission
import com.example.Extensions.handlePermissionResult
import com.example.bike.databinding.ActivityBikePhotosBinding
import com.example.bike.databinding.ActivityMainBinding
import com.example.bike.ui.main.admin.homeAdmin
import com.example.bike.ui.main.user.UserMainActivity

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.c2.setOnClickListener {
            val intent1 = Intent(this, homeAdmin::class.java)
            startActivity(intent1)
        }

        binding.bookBikeCard.setOnClickListener {
            val intent1 = Intent(this, UserMainActivity::class.java)
            startActivity(intent1)
        }

        // Check permissions and open gallery if granted
        checkAndRequestImagePermission(
            onPermissionGranted = {
                Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show()
            },
            onPermissionDenied = {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show()
            }
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        handlePermissionResult(
            requestCode = requestCode,
            grantResults = grantResults,
        )
    }
}