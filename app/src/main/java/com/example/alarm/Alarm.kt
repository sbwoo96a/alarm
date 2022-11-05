package com.example.alarm

data class Alarm(
    val id: Int,
    val time: Long,
    val enable: Boolean,
    val ringtoneUri: String,
    val day: Day
)
