package com.example.apppermission


import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private val REQUEST_CAMERA_PERMISSION = 123
    private val REQUEST_IMAGE_CAPTURE = 456

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CAMERA_PERMISSION)
        } else {
            Toast.makeText(this,"Permission already granted",Toast.LENGTH_SHORT).show()
          
            // permission already granted, proceed with camera operation
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show()

                } else {
                    // permission denied, show a message explaining why the app needs the permission
                    Toast.makeText(this,"You have to give permission",Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
                // handle other permission requests if needed
            }
        }
    }

//    private fun takePicture() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        val photoFile = createImageFile()
//        val photoURI = FileProvider.getUriForFile(this, "${packageName}.provider", photoFile)
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
//    }
//
//    private fun createImageFile(): File {
//        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        val imageFile = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
//        return imageFile
//    }

}