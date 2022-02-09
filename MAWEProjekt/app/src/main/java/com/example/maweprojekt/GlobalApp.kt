package com.example.maweprojekt

import android.app.Application

class GlobalApp : Application() {

    companion object {
        lateinit var appinstance: GlobalApp
    }

    init {
        appinstance = this
    }

    var eventList: ArrayList<Event> = ArrayList()
    var firstname: String = ""
    var lastname: String = ""
    var email: String = ""
    var phone: Long? = null

    fun checkForUser(): Boolean {
        return (firstname != "" && lastname != "" && email != "" && phone != null)
    }

    fun addEvent(location: String, date: String, time: String) {
        val newEvent = Event(
            R.drawable.ic_safe,
            text1 = location,
            text2 = "$date, $time"
        )
        eventList.add(0, newEvent)
    }
}