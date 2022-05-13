package ru.mirea.kanban.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.mirea.kanban.R
import kotlin.random.Random

/**
 * Класс "получателя уведомлений".
 * Создаваемый объект осуществляет функцию вывода уведомлений на экран при получении таковых.
 */
class NotificationsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        fun getID(): Int {
            return Random.nextInt()
//            return SimpleDateFormat("ddHHmmss").format(Date()).toString().toInt()
        }

        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")

        if ((title == null) or (text == null)) return

        val builder = NotificationCompat.Builder(context, "default")
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_navigation_board)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)

        with(NotificationManagerCompat.from(context)) {
            notify(getID(), builder.build())
        }
    }
}
