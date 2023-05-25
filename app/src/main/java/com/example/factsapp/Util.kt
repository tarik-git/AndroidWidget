package com.example.factsapp

import java.util.*

object Util {

    fun getMonth(): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        // Month begin from 0 so you have to add 1
        return calendar.get(Calendar.MONTH) + 1
    }

    fun getDayOfMonth(): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

}