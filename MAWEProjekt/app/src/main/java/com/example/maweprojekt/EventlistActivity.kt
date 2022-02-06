package com.example.maweprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_eventlist.*

class EventlistActivity : AppCompatActivity() {
    val eventList = GlobalApp.appinstance.eventList
    private val adapter = EventlistAdapter(eventList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventlist)

        val btnBack = findViewById<Button>(R.id.btnEventlistBack)
        val btnUserInfo = findViewById<Button>(R.id.btnUserCard)

        btnBack.setOnClickListener {
            finish()
        }

        btnUserInfo.setOnClickListener {
            val intent = Intent(this, UserdataActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            startActivity(intent)
        }

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }
}