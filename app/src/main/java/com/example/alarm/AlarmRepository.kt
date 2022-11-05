package com.example.alarm

class AlarmRepository(private val alarmDao: AlarmDao) {

    suspend fun addAlarm(alarmTable: AlarmTable) = alarmDao.addAlarm(alarmTable)
}