package com.example.bike

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.Extensions.checkAndRequestImagePermission
import com.example.Extensions.handlePermissionResult
import com.example.bike.ui.main.admin.homeAdmin

class MainActivity : AppCompatActivity() {

    private lateinit var adminButton: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adminButton = findViewById(R.id.adminButton)

        adminButton.setOnClickListener {
            val intent1 = Intent(this, homeAdmin::class.java)
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