package com.example.maweprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class CreateEventActivity : AppCompatActivity() {

    private lateinit var ivQRCode: ImageView
    private lateinit var etEventID : EditText
    private lateinit var btnGenerate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        ivQRCode = findViewById(R.id.ivGeneratedQR)
        etEventID = findViewById(R.id.etEventID)
        btnGenerate = findViewById(R.id.btnCreateEventGenerate)

        btnGenerate.setOnClickListener{
            val data = etEventID.text.toString().trim()
        }
    }
}