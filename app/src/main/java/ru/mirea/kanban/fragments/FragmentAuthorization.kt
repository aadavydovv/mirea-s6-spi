package ru.mirea.kanban.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.notifications.Notifications
import ru.mirea.kanban.room.kanbanTask.DBClientKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random.Default.nextInt

/**
 * Фрагмент интерфейса "Авторизация".
 * Воспроизводит layout с полями ввода имени пользователя и пароля, а также кнопками входа и регистрации.
 */
class FragmentAuthorization : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.buttonAuthAuthorize).setOnClickListener {
            val username = view.findViewById<TextInputLayout>(R.id.textInputLayoutAuthUsername).editText?.text.toString()

            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("currentUser", username)
                apply()
            }

            val dbClient = DBClientUser(requireActivity())
            lifecycleScope.launch {
                dbClient.findByName(username)
            }

            val currentUser = dbClient.awaitResult() as EntityUser

            if (currentUser.notifyTaskID != -1) {
                val dbClientTasks = DBClientKanbanTask(requireActivity())
                lifecycleScope.launch {
                    dbClientTasks.getByID(currentUser.notifyTaskID)
                }
                val notifyTask = dbClientTasks.awaitResult() as EntityKanbanTask

                lifecycleScope.launch {
                    dbClient.getByID(notifyTask.workerID)
                }
                val notifyTaskWorkerName = dbClient.awaitResult() as EntityUser

                val builder = NotificationCompat.Builder(requireContext(), "default")
                    .setSmallIcon(R.drawable.ic_navigation_board)
                    .setContentTitle("Задача выполнена!")
                    .setContentText("Сотрудник ${notifyTaskWorkerName.name} завершил задачу \"${notifyTask.name}\"")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                with(NotificationManagerCompat.from(requireContext())) {
                    notify(nextInt(), builder.build())
                }

                currentUser.notifyTaskID = -1
                lifecycleScope.launch {
                    dbClient.update(currentUser)
                }
                dbClient.awaitResult()
            }

            it.findNavController().navigate(
                FragmentAuthorizationDirections.actionFragmentAuthorizationToFragmentBoard()
            )
        }

        view.findViewById<Button>(R.id.buttonAuthRegister).setOnClickListener {
            it.findNavController().navigate(
                FragmentAuthorizationDirections.actionFragmentAuthorizationToFragmentRegistration()
            )
        }
    }
}