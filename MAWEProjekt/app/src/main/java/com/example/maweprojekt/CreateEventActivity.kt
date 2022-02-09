package com.example.maweprojekt

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class CreateEventActivity : AppCompatActivity() {

    private lateinit var ivQRCode: ImageView
    private lateinit var etEventID: EditText
    private lateinit var btnGenerate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        ivQRCode = findViewById(R.id.ivGeneratedQR)
        etEventID = findViewById(R.id.etEventID)
        btnGenerate = findViewById(R.id.btnCreateEventGenerate)
        btnGenerate.isEnabled = false
        val btnPlay = findViewById<Button>(R.id.btnCreateEventPlay)
        btnPlay.isEnabled = false
        val btnBack = findViewById<Button>(R.id.btnCreateEventBack)

        etEventID.addTextChangedListener(twEventID)

        btnGenerate.setOnClickListener {
            btnGenerate.isEnabled = false
            btnPlay.isEnabled = true
            etEventID.isEnabled = false

            val now = Calendar.getInstance()
            val currentDay: Int = now.get(Calendar.DAY_OF_MONTH)
            val currentMonth: Int = now.get(Calendar.MONTH)
            val currentYear: Int = now.get(Calendar.YEAR)
            val currentHour: Int = now.get(Calendar.HOUR_OF_DAY) //hour in 24h format
            val currentMinute: Int = now.get(Calendar.MINUTE)

            // add event to EventList
            GlobalApp.appinstance.addEvent(
                etEventID.text.toString(),
                "$currentDay.$currentMonth.$currentYear",
                "$currentHour:$currentMinute"
            )

            // create data for QR-Code
            val data = "playitsafe-" + etEventID.text.toString().trim() +
                    "-$currentDay.$currentMonth.$currentYear"

            val writer = QRCodeWriter()
            try {
                val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                val qrwidth = bitMatrix.width
                val qrheight = bitMatrix.height
                val bmp = Bitmap.createBitmap(qrwidth, qrheight, Bitmap.Config.RGB_565)
                for (x in 0 until qrwidth) {
                    for (y in 0 until qrheight) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }
                ivQRCode.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }

        btnPlay.setOnClickListener {
            alertQRcodePlay()
        }

        btnBack.setOnClickListener {
            if (!etEventID.isEnabled) {
                alertQRcodeBack()
            } else {
                finish()
            }
        }
    }

    private var twEventID: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val evID: String = etEventID.text.toString().trim()
            if (evID.length > 4) {
                btnGenerate.isEnabled = true
            }
        }

        override fun afterTextChanged(s: Editable) {}
    }

    private fun alertQRcodePlay() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alertQRcodePlay_title))
        builder.setMessage(getString(R.string.alertQRcodePlay_message))
        builder.setPositiveButton(getString(R.string.start_playing)) { dialog, _ ->
            dialog.cancel()

            val eventStartNotification = "Started Event: " + etEventID.text
            Toast.makeText(applicationContext, eventStartNotification, Toast.LENGTH_LONG).show()

            val intent = Intent(this, PlaySettingsActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        builder.setNegativeButton(getString(R.string.btnBack)) { dialog, _ ->
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun alertQRcodeBack() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alertQRcodeBack_title))
        builder.setMessage(getString(R.string.alertQRcodeBack_message))
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