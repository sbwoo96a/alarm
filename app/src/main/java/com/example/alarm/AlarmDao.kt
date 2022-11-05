package com.example.alarm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlarm(alarmTable: AlarmTable)
}