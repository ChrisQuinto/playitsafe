package com.example.maweprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class PlayTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_two)

        val points = intent.getIntExtra("EXTRA_POINTS", 20)
        val beginner = intent.getBooleanExtra("EXTRA_BEGINNER", false)

        val tvP1name = findViewById<TextView>(R.id.tvPlayTwoP1name)
        tvP1name.text = intent.getStringExtra("EXTRA_P1")
        val tvP2name = findViewById<TextView>(R.id.tvPlayTwoP2name)
        tvP2name.text = intent.getStringExtra("EXTRA_P2")

        val tvP1points = findViewById<TextView>(R.id.tvPlayTwoP1points)
        tvP1points.text = points.toString()
        val tvP2points = findViewById<TextView>(R.id.tvPlayTwoP2points)
        tvP2points.text = points.toString()

        var pointCounter: String

        val btnP1add = findViewById<Button>(R.id.btnPlayTwoP1add)
        val btnP1sub = findViewById<Button>(R.id.btnPlayTwoP1sub)
        val btnP2add = findViewById<Button>(R.id.btnPlayTwoP2add)
        val btnP2sub = findViewById<Button>(R.id.btnPlayTwoP2sub)

        val btnBack = findViewById<Button>(R.id.btnPlayTwoBack)

        if (beginner) {
            if (Random.nextBoolean()) {
                alertPlayTwoBeginner(tvP1name.text.toString())
            } else {
                alertPlayTwoBeginner(tvP2name.text.toString())
            }
        }

        btnP1add.setOnClickListener {
            pointCounter = (tvP1points.text.toString().toInt() + 1).toString()
            tvP1points.text = pointCounter
        }

        btnP1sub.setOnClickListener {
            pointCounter = (tvP1points.text.toString().toInt() - 1).toString()
            tvP1points.text = pointCounter
        }

        btnP2add.setOnClickListener {
            pointCounter = (tvP2points.text.toString().toInt() + 1).toString()
            tvP2points.text = pointCounter
        }

        btnP2sub.setOnClickListener {
            pointCounter = (tvP2points.text.toString().toInt() - 1).toString()
            tvP2points.text = pointCounter
        }

        btnBack.setOnClickListener {
            alertPlayTwoBack()
        }
    }

    private fun alertPlayTwoBeginner(beginner: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(beginner + getString(R.string.will_begin))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun alertPlayTwoBack() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alertPlayTwoBack_message))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            dialog.cancel()
            finish()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }
}