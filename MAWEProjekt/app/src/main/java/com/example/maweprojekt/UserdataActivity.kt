package com.example.maweprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_userdata.*
import kotlinx.android.synthetic.main.activity_userdata.view.*

class UserdataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdata)

        updateUserData()

        val btnBack = findViewById<Button>(R.id.btnUserdataBack)

        btnBack.setOnClickListener{
            finish()
        }
    }

    private fun updateUserData() {
        tlUserData.tvFirstNameTable.text = GlobalApp.appinstance.firstname
        tlUserData.tvLastNameTable.text = GlobalApp.appinstance.lastname
        tlUserData.tvEmailTable.text = GlobalApp.appinstance.email
        if (GlobalApp.appinstance.phone.toString() == "null") {
            tlUserData.tvPhoneTable.text = ""
        } else {
            tlUserData.tvPhoneTable.text = GlobalApp.appinstance.phone.toString()
        }
    }
}