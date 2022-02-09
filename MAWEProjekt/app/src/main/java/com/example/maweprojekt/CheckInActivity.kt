package com.example.maweprojekt

import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.activity_checkin.*

private const val CAMERA_REQUEST_CODE = 101

class CheckInActivity : AppCompatActivity() {

    private lateinit var qrScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)

        setupPermissions()
        codeScanner()

        val btnConfirm = findViewById<Button>(R.id.btnCheckInConfirm)
        btnConfirm.isEnabled = false
        val btnBack = findViewById<Button>(R.id.btnCheckInBack)

        btnConfirm.setOnClickListener {
            val now = Calendar.getInstance()
            val currentDay: Int = now.get(Calendar.DAY_OF_MONTH)
            val currentMonth: Int = now.get(Calendar.MONTH)
            val currentYear: Int = now.get(Calendar.YEAR)
            val currentDate = "$currentDay.$currentMonth.$currentYear"
            if (currentDate == tvDate.text) {
                updateEventlist()
                checkinSuccessNotification()
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("EXTRA_INFO", "This is my Info")
                }
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            } else {
                alertOutdatedEvent()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun updateEventlist() {
        val now = Calendar.getInstance()
        val currentHour: Int = now.get(Calendar.HOUR_OF_DAY) //hour in 24h format
        val currentMinute: Int = now.get(Calendar.MINUTE)
        val currentTime = "$currentHour:$currentMinute"
        GlobalApp.appinstance.addEvent(
            tvLocation.text.toString(),
            tvDate.text.toString(), currentTime
        )
    }

    private fun checkinSuccessNotification() {
        val checkinNotification = getString(R.string.toast_checkin1) +
                tvLocation.text + getString(R.string.toast_checkin2) +
                "${GlobalApp.appinstance.firstname} ${GlobalApp.appinstance.lastname}."
        Toast.makeText(applicationContext, checkinNotification, Toast.LENGTH_LONG).show()
    }

    private fun alertOutdatedEvent() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alertOutdatedEvent_title))
        builder.setMessage(getString(R.string.alertOutdatedEvent_message))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            updateEventlist()
            checkinSuccessNotification()
            dialog.cancel()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_INFO", "This is my Info")
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun checkQRCode(qrstring: String) {
        //QRCodes for this app are in the format "playitsafe-locationID-date"
        val delim = "-"
        val arr = qrstring.split(delim).toTypedArray()
        if (arr[0] == "playitsafe") {
            tvLocation.text = arr[1]
            tvDate.text = arr[2]
            btnCheckInConfirm.isEnabled = true
        } else {
            val wrongCodeNotification = getString(R.string.toast_unknownCode)
            Toast.makeText(applicationContext, wrongCodeNotification, Toast.LENGTH_LONG).show()
        }
    }

    private fun codeScanner() {
        qrScanner = CodeScanner(this, csQRscanner)

        qrScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    checkQRCode(it.text)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        csQRscanner.setOnClickListener {
            qrScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        qrScanner.startPreview()
    }

    override fun onPause() {
        qrScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions() {
        val permission: Int = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "Camera permission is needed to use this app",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    //Permission granted!
                }
            }
        }
    }
}