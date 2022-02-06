package com.example.maweprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_regdata.*

class RegdataActivity : AppCompatActivity() {

    lateinit var firstname: EditText
    lateinit var lastname: EditText
    lateinit var email: EditText
    lateinit var phone: EditText

    lateinit var btnSave: Button
    lateinit var btnProceed: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regdata)

        val howtoproceed = intent.getStringExtra("EXTRA_INFO")

        btnSave = findViewById(R.id.btnRegSave)
        btnSave.isEnabled = false
        btnProceed = findViewById(R.id.btnRegProceed)
        btnProceed.isEnabled = false
        val btnBack = findViewById<Button>(R.id.btnRegBack)

        firstname = findViewById(R.id.etFirstName)
        lastname = findViewById(R.id.etLastName)
        email = findViewById(R.id.etEmail)
        phone = findViewById(R.id.etPhone)

        if (GlobalApp.appinstance.checkUser()) {
            firstname.setText(GlobalApp.appinstance.firstname)
            lastname.setText(GlobalApp.appinstance.lastname)
            email.setText(GlobalApp.appinstance.email)
            phone.setText(GlobalApp.appinstance.phone.toString())
            btnProceed.isEnabled = true
        }

        firstname.addTextChangedListener(twSave)
        lastname.addTextChangedListener(twSave)
        email.addTextChangedListener(twSave)
        phone.addTextChangedListener(twSave)

        btnSave.setOnClickListener{
            GlobalApp.appinstance.firstname = etFirstName.text.toString()
            GlobalApp.appinstance.lastname = etLastName.text.toString()
            GlobalApp.appinstance.email = etEmail.text.toString()
            GlobalApp.appinstance.phone = etPhone.text.toString().toInt()
            btnProceed.isEnabled = true
            btnSave.isEnabled = false
        }

        btnProceed.setOnClickListener {
            if (howtoproceed == "Create") {
                val intent = Intent(this, CreateEventActivity::class.java).apply {
                    putExtra("EXTRA_INFO", "This is my Info")
                }
                startActivity(intent)
            }
            else if (howtoproceed == "CheckIn") {
                val intent = Intent(this, CheckInActivity::class.java).apply {
                    putExtra("EXTRA_INFO", "This is my Info")
                }
                startActivity(intent)
            }
        }

        btnBack.setOnClickListener{
            finish()
        }
    }

    private var twSave: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val fn: String = firstname.text.toString().trim()
            val ln: String = lastname.text.toString().trim()
            val em: String = email.text.toString().trim()
            val ph: String = phone.text.toString().trim()
            print(fn.isNotEmpty() && ln.isNotEmpty() && em.isNotEmpty() && ph.isNotEmpty())
            btnSave.isEnabled = (fn.isNotEmpty() && ln.isNotEmpty() && em.isNotEmpty() && ph.isNotEmpty())
        }
        override fun afterTextChanged(s: Editable) {}
    }
}