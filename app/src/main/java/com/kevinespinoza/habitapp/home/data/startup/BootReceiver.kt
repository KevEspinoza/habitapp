package com.kevinespinoza.habitapp.home.data.startup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kevinespinoza.habitapp.home.data.extension.goAsync
import com.kevinespinoza.habitapp.home.data.local.HomeDao
import com.kevinespinoza.habitapp.home.data.mapper.toDomain
import com.kevinespinoza.habitapp.home.domain.alarm.AlarmHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver: BroadcastReceiver() {
    @Inject
    lateinit var alarmHandler: AlarmHandler

    @Inject
    lateinit var homeDao: HomeDao

    override fun onReceive(p0: Context?, p1: Intent?) = goAsync {
        if(p0 == null || p1 == null) return@goAsync

        if(p1.action != Intent.ACTION_BOOT_COMPLETED) return@goAsync
        
        val items = homeDao.getAllHabits()
        items.forEach{
            alarmHandler.setRecurringAlarm(it.toDomain())
        }
    }
}