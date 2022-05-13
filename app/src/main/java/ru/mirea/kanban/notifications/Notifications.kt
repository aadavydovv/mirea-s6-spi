package ru.mirea.kanban.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*
import kotlin.random.Random

/**
 * Класс "уведомления".
 * Создаваемый объект осуществляет отправку уведомлений в заданное время.
 */
class Notifications(private val context: Context) {

    /**
     * Метод отправки уведомления.
     *
     * @param title заголовок уведомления.
     * @param text текст уведомления.
     * @param hour час отправки уведомления.
     * @param minute минута отправки уведомления.
     */
    fun sendNotification(title: String, text: String, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, NotificationsReceiver::class.java).let { intent ->
            intent.putExtra("title", title)
            intent.putExtra("text", text)
            PendingIntent.getBroadcast(context, Random.nextInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )
    }
}