package ru.mirea.kanban

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.sqlite.SQLiteConstraintException
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import ru.mirea.kanban.notifications.NotificationsReceiver
import ru.mirea.kanban.room.KanbanDatabase
import ru.mirea.kanban.room.kanbanList.DBClientKanbanList
import ru.mirea.kanban.room.kanbanList.EntityKanbanList
import ru.mirea.kanban.room.kanbanTask.DBClientKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask

/**
 * Главное активити.
 * Основной элемент для функционирования интерфейса и навигации.
 * Инициализирует важные элементы приложения (например, базу данных).
 *
 * @property appBarConfiguration свойство-объект конфигурации панели навигации приложения.
 * @property db объект для взаимодействия с базой данных приложения.
 * @property notificationsReceiver свойство-объект для получения уведомлений.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var db: KanbanDatabase
    lateinit var notificationsReceiver: NotificationsReceiver

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDB()
        initNotifications()
        initViews()
    }

    /**
     * Инициализация необходимых view.
     */
    private fun initViews() {
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        navView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navView.menu)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.visibility = View.GONE
        supportActionBar?.hide()

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt("currentListID", 1)
            apply()
        }
    }

    /**
     * Инициализация базы данных.
     */
    private fun initDB() {
        db = Room.databaseBuilder(
            applicationContext,
            KanbanDatabase::class.java, "kanban-database"
        ).build()

        val entityListActive = EntityKanbanList("Активные", 1)
        val entityListFinished = EntityKanbanList("Завершённые", 2)

        val dbClient = DBClientKanbanList(this)
        lifecycleScope.launch {
            try {
                dbClient.insertAll(entityListActive, entityListFinished)
            } catch (e: SQLiteConstraintException) {
            }
        }
        dbClient.awaitResult()
    }

    /**
     * Инициализация оповещений.
     */
    private fun initNotifications() {
        val name = "Задача доски"
        val descriptionText = "Уведомления о задаче доски"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("default", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        notificationsReceiver = NotificationsReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(notificationsReceiver, filter)
    }
}