package com.karlituxd.pickimage1

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val startForResult =
            this@MainActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    //TODO
                    val intent = result.data
                    if (intent != null) {
                        val str = intent.data.toString()
                        findViewById<ImageView>(R.id.my_image).setImageURI(intent.data)
                        Log.e("MY_TAG", str)
                        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
                    }
                }
            }

        findViewById<Button>(R.id.my_button).setOnClickListener {
            val enrollIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startForResult.launch(enrollIntent)
        }
    }
}