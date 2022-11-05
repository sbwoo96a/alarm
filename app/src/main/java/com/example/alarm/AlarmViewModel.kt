package com.example.alarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application): AndroidViewModel(application) {

    private val repository: AlarmRepository

    init {
        val alarmDao = AlarmDatabase.getDatabase(application).alarmDao()
        repository = AlarmRepository(alarmDao)

    }

    fun addAlarm(alarmTable: AlarmTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAlarm(alarmTable)
        }
    }

}