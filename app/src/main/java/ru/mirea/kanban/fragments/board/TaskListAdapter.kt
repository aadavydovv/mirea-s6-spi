package ru.mirea.kanban.fragments.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.mirea.kanban.MainActivity
import ru.mirea.kanban.R
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser

/**
 * Адаптер списка задач для recycler view.
 * Предоставляет recycler view необходимую информацию из получаемого списка задач.
 */
class TaskListAdapter(
    private val courseEntries: List<EntityKanbanTask>, private val mainActivity: FragmentActivity
) :
    RecyclerView.Adapter<TaskListAdapter.TaskEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_task_entry, parent, false)

        return TaskEntryViewHolder(view, mainActivity)
    }

    override fun onBindViewHolder(holder: TaskEntryViewHolder, position: Int) {
        holder.bind(courseEntries[position])
    }

    override fun getItemCount() = courseEntries.size

    /**
     * Класс view holder для записи "задача".
     * Создаваемый объект выполняет функцию содержания необходимой информации для recycler view.
     */
    class TaskEntryViewHolder(itemView: View, private val mainActivity: FragmentActivity) : RecyclerView.ViewHolder(itemView) {
        private val nameOfTask: TextView = itemView.findViewById(R.id.textViewTaskName)
        private val creatorOfTask: TextView = itemView.findViewById(R.id.textViewTaskCreator)
        private val deadlineOfTask: TextView = itemView.findViewById(R.id.textViewTaskDeadline)
        private val taskAssignedTo: TextView = itemView.findViewById(R.id.textViewTaskAssigned)

        fun bind(taskEntry: EntityKanbanTask) {
            itemView.setOnClickListener {
                val action = FragmentBoardDirections.actionFragmentBoardToFragmentTask()
                action.taskID = taskEntry.id
                it.findNavController().navigate(action)
            }

            nameOfTask.text = taskEntry.name
            creatorOfTask.text = taskEntry.creator

            if (taskEntry.deadline != "") {
                itemView.findViewById<LinearLayout>(R.id.layoutTaskDeadline).visibility = View.VISIBLE
                deadlineOfTask.text = taskEntry.deadline
            }

            if (taskEntry.workerID != -1) {
                itemView.findViewById<LinearLayout>(R.id.layoutTaskAssigned).visibility = View.VISIBLE

                val dbClient = DBClientUser(mainActivity)
                mainActivity.lifecycleScope.launch {
                    dbClient.getByID(taskEntry.workerID)
                }
                val worker = dbClient.awaitResult() as EntityUser
                taskAssignedTo.text = worker.name
            }
        }
    }
}