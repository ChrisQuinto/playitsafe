package com.example.maweprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val btnBack = findViewById<Button>(R.id.btnPlayBack)

        btnBack.setOnClickListener{
            finish()
        }
    }
}