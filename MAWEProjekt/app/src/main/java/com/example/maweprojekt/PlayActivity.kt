package com.example.maweprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.Group

class PlayActivity : AppCompatActivity() {

    private lateinit var tvP1name: TextView
    private lateinit var tvP2name: TextView
    private lateinit var tvP3name: TextView
    private lateinit var tvP4name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val players = intent.getIntExtra("EXTRA_PLAYERS", 4)
        val points = intent.getIntExtra("EXTRA_POINTS", 20)
        val beginner = intent.getBooleanExtra("EXTRA_BEGINNER", false)

        val groupP4 = findViewById<Group>(R.id.grPlayer4)

        if (players == 4) {
            groupP4.visibility = View.VISIBLE
        }

        tvP1name = findViewById(R.id.tvPlayP1name)
        tvP2name = findViewById(R.id.tvPlayP2name)
        tvP3name = findViewById(R.id.tvPlayP3name)
        tvP4name = findViewById(R.id.tvPlayP4name)

        tvP1name.text = intent.getStringExtra("EXTRA_P1")
        tvP2name.text = intent.getStringExtra("EXTRA_P2")
        tvP3name.text = intent.getStringExtra("EXTRA_P3")
        tvP4name.text = intent.getStringExtra("EXTRA_P4")

        val tvP1points = findViewById<TextView>(R.id.tvPlayP1points)
        tvP1points.text = points.toString()
        val tvP2points = findViewById<TextView>(R.id.tvPlayP2points)
        tvP2points.text = points.toString()
        val tvP3points = findViewById<TextView>(R.id.tvPlayP3points)
        tvP3points.text = points.toString()
        val tvP4points = findViewById<TextView>(R.id.tvPlayP4points)
        tvP4points.text = points.toString()

        var pointCounter: String

        val btnP1add = findViewById<Button>(R.id.btnPlayP1add)
        val btnP1sub = findViewById<Button>(R.id.btnPlayP1sub)
        val btnP2add = findViewById<Button>(R.id.btnPlayP2add)
        val btnP2sub = findViewById<Button>(R.id.btnPlayP2sub)
        val btnP3add = findViewById<Button>(R.id.btnPlayP3add)
        val btnP3sub = findViewById<Button>(R.id.btnPlayP3sub)
        val btnP4add = findViewById<Button>(R.id.btnPlayP4add)
        val btnP4sub = findViewById<Button>(R.id.btnPlayP4sub)

        val btnBack = findViewById<Button>(R.id.btnPlayBack)

        if (beginner) {
            determineBeginner(players)
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

        btnP3add.setOnClickListener {
            pointCounter = (tvP3points.text.toString().toInt() + 1).toString()
            tvP3points.text = pointCounter
        }

        btnP3sub.setOnClickListener {
            pointCounter = (tvP3points.text.toString().toInt() - 1).toString()
            tvP3points.text = pointCounter
        }

        btnP4add.setOnClickListener {
            pointCounter = (tvP4points.text.toString().toInt() + 1).toString()
            tvP4points.text = pointCounter
        }

        btnP4sub.setOnClickListener {
            pointCounter = (tvP4points.text.toString().toInt() - 1).toString()
            tvP4points.text = pointCounter
        }

        btnBack.setOnClickListener {
            alertPlayBack()
        }
    }

    private fun determineBeginner(participants: Int) {
        if (participants == 3) {
            when ((1..3).random()) {
                1 -> {
                    alertPlayBeginner(tvP1name.text.toString())
                }
                2 -> {
                    alertPlayBeginner(tvP2name.text.toString())
                }
                3 -> {
                    alertPlayBeginner(tvP3name.text.toString())
                }
            }
        } else if (participants == 4) {
            when ((1..4).random()) {
                1 -> {
                    alertPlayBeginner(tvP1name.text.toString())
                }
                2 -> {
                    alertPlayBeginner(tvP2name.text.toString())
                }
                3 -> {
                    alertPlayBeginner(tvP3name.text.toString())
                }
                4 -> {
                    alertPlayBeginner(tvP4name.text.toString())
                }
            }
        }
    }

    private fun alertPlayBeginner(beginner: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(beginner + getString(R.string.will_begin))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun alertPlayBack() {
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