package com.kevinespinoza.habitapp.home.data.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.kevinespinoza.habitapp.R
import com.kevinespinoza.habitapp.home.data.extension.goAsync
import com.kevinespinoza.habitapp.home.domain.alarm.AlarmHandler
import com.kevinespinoza.habitapp.home.domain.models.Habit
import com.kevinespinoza.habitapp.home.domain.repository.HomeRepository
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver: BroadcastReceiver() {
    companion object{
        const val HABIT_ID = "habit_id"
        private const val CHANNEL_ID = "habits_channel"
    }

    @Inject
    lateinit var repository: HomeRepository
    @Inject
    lateinit var alarmHandler: AlarmHandler

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if(context == null || intent == null) return@goAsync
        val id = intent.getStringExtra(HABIT_ID) ?: return@goAsync
        val habit = repository.getHabitById(id)
        createNotificationChannel(context)
        if(!habit.completedDates.contains(LocalDate.now())){
            showNotification(context,habit)
        }
        alarmHandler.setRecurringAlarm(habit)
    }

    private fun showNotification(context: Context, habit: Habit) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(habit.name)
            .build()

        notificationManager.notify(habit.id.hashCode(), notification)
    }

    private fun createNotificationChannel(context: Context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Habit Reminder Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Get your habits reminder!"
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}