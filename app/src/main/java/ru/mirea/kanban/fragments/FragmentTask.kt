package ru.mirea.kanban.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.room.kanbanTask.DBClientKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser
import java.text.SimpleDateFormat
import java.util.*

/**
 * Фрагмент интерфейса "Задача".
 * Воспроизводит layout с полями наименования и описания задачи, а также кнопками: оставить комменатрий,
 * прикрепить файл, установить напоминание, установить срок выполнения, назначить пользователя,
 * оповестить о завершении. Некоторые кнопки доступны лишь для руководящей роли.
 */
class FragmentTask : Fragment() {

    private val args: FragmentTaskArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (args.taskID == -1) {
            view.findViewById<Button>(R.id.buttonTaskAdd).setOnClickListener {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val currentUser = sharedPref?.getString("currentUser", "oops")!!
                val currentListID = sharedPref.getInt("currentListID", 1)

                val newTaskName =
                    view.findViewById<TextInputLayout>(R.id.textInputLayoutTaskName).editText?.text.toString()
                val newTaskDescription =
                    view.findViewById<TextInputLayout>(R.id.textInputLayoutTaskDescription).editText?.text.toString()

                val dbClient = DBClientKanbanTask(requireActivity())
                lifecycleScope.launch {
                    dbClient.insertAll(
                        EntityKanbanTask(
                            newTaskName,
                            currentUser,
                            currentListID,
                            newTaskDescription
                        )
                    )
                }
                dbClient.awaitResult()

                findNavController().popBackStack()
            }

            return
        }

        view.findViewById<Button>(R.id.buttonTaskAdd).visibility = View.GONE
        view.findViewById<LinearLayout>(R.id.layoutTaskEndButtons).visibility = View.VISIBLE
        view.findViewById<LinearLayout>(R.id.layoutTaskOptions).visibility = View.VISIBLE

        val dbClient = DBClientKanbanTask(requireActivity())
        lifecycleScope.launch {
            dbClient.getByID(args.taskID)
        }

        val task = dbClient.awaitResult() as EntityKanbanTask

        view.findViewById<TextInputLayout>(R.id.textInputLayoutTaskName).editText?.setText(task.name)
        view.findViewById<TextInputLayout>(R.id.textInputLayoutTaskDescription).editText?.setText(
            task.description
        )

        view.findViewById<Button>(R.id.buttonTaskPlanningDeadline).setOnClickListener {

            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
            datePicker.show(requireActivity().supportFragmentManager, "tag")

            datePicker.addOnPositiveButtonClickListener {
                val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                utc.timeInMillis = it
                val format = SimpleDateFormat("dd.MM.yyyy")
                val deadline: String = format.format(utc.time)

//                val deadline = datePicker.selection.toString()

                lifecycleScope.launch {
                    task.deadline = deadline
                    dbClient.update(task)
                }
                dbClient.awaitResult()
            }
        }

        view.findViewById<Button>(R.id.buttonTaskAssign).setOnClickListener {
            val action = FragmentTaskDirections.actionFragmentTaskToFragmentUsers()
            action.taskID = task.id
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.buttonTaskCommentsView).setOnClickListener {
            view.findNavController().navigate(
                FragmentTaskDirections.actionFragmentTaskToFragmentCommentList(args.taskID)
            )
        }

        view.findViewById<Button>(R.id.buttonTaskEndNotify).setOnClickListener {
            task.listID = 2
            lifecycleScope.launch {
                dbClient.update(task)
            }
            dbClient.awaitResult()

            val dbClientUsers = DBClientUser(requireActivity())
            lifecycleScope.launch {
                dbClientUsers.findByName(task.creator)
            }
            val creator = dbClientUsers.awaitResult() as EntityUser

            creator.notifyTaskID = task.id

            lifecycleScope.launch {
                dbClientUsers.update(creator)
            }
            dbClientUsers.awaitResult()

            findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.buttonTaskEnd).setOnClickListener {
            task.listID = 2
            lifecycleScope.launch {
                dbClient.update(task)
            }
            dbClient.awaitResult()

            findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.buttonTaskFilesView).setOnClickListener {
            view.findNavController().navigate(
                FragmentTaskDirections.actionFragmentTaskToFragmentFiles(args.taskID)
            )
        }

        view.findViewById<Button>(R.id.buttonTaskChangeList).setOnClickListener {
            val action = FragmentTaskDirections.actionFragmentTaskToFragmentListSelect()
            action.taskID = args.taskID
            view.findNavController().navigate(action)
        }
    }
}