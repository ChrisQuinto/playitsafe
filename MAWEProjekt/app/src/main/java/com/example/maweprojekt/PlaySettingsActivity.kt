package com.example.maweprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.TextView

class PlaySettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playsettings)

        val sbPlayers = findViewById<SeekBar>(R.id.sbPlayerCount)
        val tvPlayers = findViewById<TextView>(R.id.tvSeekBarLabel)

        val cbBeginner = findViewById<CheckBox>(R.id.cbBeginner)

        val btnStart = findViewById<Button>(R.id.btnStartPlaying)
        val btnBack = findViewById<Button>(R.id.btnSettingsBack)

        sbPlayers.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvPlayers.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        cbBeginner.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {

            }
        }

        btnStart.setOnClickListener{
            val intent = Intent(this, PlayActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            startActivity(intent)
        }

        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}