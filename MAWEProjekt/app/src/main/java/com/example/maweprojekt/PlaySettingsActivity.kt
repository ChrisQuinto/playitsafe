package com.example.maweprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PlaySettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playsettings)

        val btnStart = findViewById<Button>(R.id.btnStartPlaying)
        val btnBack = findViewById<Button>(R.id.btnSettingsBack)

        btnStart.setOnClickListener{
            val intent = Intent(this, PlayActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            startActivity(intent)
        }

        btnBack.setOnClickListener{
            finish()
        }
    }
}