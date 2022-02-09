package com.example.maweprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_eventlist.*

class EventlistActivity : AppCompatActivity(), OnEventClickListener {

    private val eventList = GlobalApp.appinstance.eventList

    private val adapter = EventlistAdapter(eventList, this)

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

    override fun onEventClicked(event: Event, eventPosition: Int) {
        if (eventPosition != -1) {
            if (event.imageResource == R.drawable.ic_safe) {
                alertEventClicked(event, eventPosition)
            }
        }
    }

    private fun alertEventClicked(event: Event, eventPosition: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alertEventClicked_title) + event.text1 + "?")
        builder.setMessage(getString(R.string.alertEventClicked_message))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            event.imageResource = R.drawable.ic_unsafe
            adapter.notifyItemChanged(eventPosition)
            dialog.cancel()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }
}