package com.example

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.Constants.KEY_PERMISSION_GRANTED
import com.example.Constants.PREFS_NAME
import com.example.Constants.REQUEST_CODE_PICK_IMAGE

object Extensions {
    fun Context.createAlertDialog(
        isCancelable: Boolean = true,
        positiveButtonText: String = "OK",
        negativeButtonText: String = "Cancel",
        onPositiveClick: (() -> Unit)? = null,
    ): AlertDialog.Builder {
        val builder = AlertDialog.Builder(this)
            .setCancelable(isCancelable)

        // Set the positive button if the action is provided
        builder.setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveClick?.invoke()
        }

        // Set the negative button if the action is provided
        builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            dialog.dismiss()
        }

        return builder
    }

    fun Context.checkAndRequestImagePermission(
        onPermissionGranted: (() -> Unit)?=null,
        onPermissionDenied: (() -> Unit)? = null
    ) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                // Android 14 (API level 34) and above
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this as Activity,
                        arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                        REQUEST_CODE_PICK_IMAGE
                    )
                } else {
                    // Permission granted, proceed with the action
                    if (onPermissionGranted != null) {
                        onPermissionGranted()
                    }
                }
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                // Android 10 (API level 29) to Android 13 (API level 33)
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this as Activity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_CODE_PICK_IMAGE
                    )
                } else {
                    // Permission granted, proceed with the action
                    if (onPermissionGranted != null) {
                        onPermissionGranted()
                    }
                }
            }
            else -> {
                // For Android versions below 10, no special permission needed
                if (onPermissionGranted != null) {
                    onPermissionGranted()
                }
            }
        }
    }
    fun Activity.handlePermissionResult(
        requestCode: Int,
        grantResults: IntArray,
        onPermissionGranted: (() -> Unit)? = null,
        onPermissionDenied: (() -> Unit)? = null
    ) {
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, store the status
                editor.putBoolean(KEY_PERMISSION_GRANTED, true)
                editor.apply()
                if (onPermissionGranted != null) {
                    onPermissionGranted()
                }
            } else {
                // Permission denied, store the status
                editor.putBoolean(KEY_PERMISSION_GRANTED, false)
                editor.apply()
                onPermissionDenied?.invoke()
            }
        }
    }
}