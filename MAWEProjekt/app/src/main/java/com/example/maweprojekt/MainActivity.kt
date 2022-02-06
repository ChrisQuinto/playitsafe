package com.example.maweprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCreateEvent = findViewById<Button>(R.id.btnMainCreateEvent)
        val btnCheckIn = findViewById<Button>(R.id.btnMainCheckIn)
        val btnPlay = findViewById<Button>(R.id.btnMainPlay)
        val btnDatabase = findViewById<Button>(R.id.btnMainDatabase)

        btnCreateEvent.setOnClickListener {
            val intent = Intent(this, RegdataActivity::class.java).also {
                it.putExtra("EXTRA_INFO", "Create")
            }
            startActivity(intent)
        }

        btnCheckIn.setOnClickListener {
            val intent = Intent(this, RegdataActivity::class.java).also {
                it.putExtra("EXTRA_INFO", "CheckIn")
            }
            startActivity(intent)
        }

        btnPlay.setOnClickListener {
            val intent = Intent(this, PlaySettingsActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            startActivity(intent)
        }

        btnDatabase.setOnClickListener {
            val intent = Intent(this, EventlistActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            startActivity(intent)
        }
    }
}

