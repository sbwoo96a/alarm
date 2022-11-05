package com.example.alarm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm")
data class AlarmTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: Long,
    val enable: Boolean,
    val ringtoneUri: String,
    val day: Day
)
