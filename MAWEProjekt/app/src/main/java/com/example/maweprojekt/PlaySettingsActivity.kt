package com.example.maweprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class PlaySettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playsettings)

        val sbPlayers = findViewById<SeekBar>(R.id.sbPlayerCount)
        val tvPlayers = findViewById<TextView>(R.id.tvSeekBarLabel)

        val etPoints = findViewById<EditText>(R.id.etStartingPoints)

        val cbBeginner = findViewById<CheckBox>(R.id.cbBeginner)
        cbBeginner.isChecked = false

        val etP1name = findViewById<EditText>(R.id.etP1name)
        etP1name.setText(getString(R.string.player_1))
        val etP2name = findViewById<EditText>(R.id.etP2name)
        etP2name.setText(getString(R.string.player_2))
        val etP3name = findViewById<EditText>(R.id.etP3name)
        etP3name.setText(getString(R.string.player_3))
        val etP4name = findViewById<EditText>(R.id.etP4name)
        etP4name.setText(getString(R.string.player_4))

        val btnStart = findViewById<Button>(R.id.btnStartPlaying)
        val btnBack = findViewById<Button>(R.id.btnSettingsBack)

        sbPlayers.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val currentValue = progress.toString().toInt() + 2
                tvPlayers.text = currentValue.toString()
                when (currentValue) {
                    3 -> {
                        etP3name.isEnabled = true
                        etP3name.isVisible = true
                        etP4name.isEnabled = false
                        etP4name.isVisible = false
                    }
                    4 -> {
                        etP3name.isEnabled = true
                        etP3name.isVisible = true
                        etP4name.isEnabled = true
                        etP4name.isVisible = true
                    }
                    else -> {
                        etP3name.isEnabled = false
                        etP3name.isVisible = false
                        etP4name.isEnabled = false
                        etP4name.isVisible = false
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnStart.setOnClickListener {
            if (etPoints.text.toString() == "") {
                etPoints.setText(getString(R.string.default_starting_points))
            }
            if (tvPlayers.text == "2") {
                val intent = Intent(this, PlayTwoActivity::class.java).also {
                    it.putExtra("EXTRA_POINTS", etPoints.text.toString().toInt())
                    it.putExtra("EXTRA_BEGINNER", cbBeginner.isChecked)
                    it.putExtra("EXTRA_P1", etP1name.text.toString())
                    it.putExtra("EXTRA_P2", etP2name.text.toString())
                }
                startActivity(intent)
            } else {
                val intent = Intent(this, PlayActivity::class.java).also {
                    it.putExtra("EXTRA_PLAYERS", tvPlayers.text.toString().toInt())
                    it.putExtra("EXTRA_POINTS", etPoints.text.toString())
                    it.putExtra("EXTRA_BEGINNER", cbBeginner.isChecked)
                    it.putExtra("EXTRA_P1", etP1name.text.toString())
                    it.putExtra("EXTRA_P2", etP2name.text.toString())
                    it.putExtra("EXTRA_P3", etP3name.text.toString())
                    it.putExtra("EXTRA_P4", etP4name.text.toString())
                }
                startActivity(intent)
            }
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}