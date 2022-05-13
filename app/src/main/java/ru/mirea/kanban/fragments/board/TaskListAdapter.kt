package ru.mirea.kanban.fragments.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.kanban.R

/**
 * Адаптер списка задач для recycler view.
 * Предоставляет recycler view необходимую информацию из получаемого списка задач.
 */
class TaskListAdapter(
    private val courseEntries: List<TaskEntry>,
) :
    RecyclerView.Adapter<TaskListAdapter.TaskEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_task_entry, parent, false)

        return TaskEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskEntryViewHolder, position: Int) {
        holder.bind(courseEntries[position])
    }

    override fun getItemCount() = courseEntries.size

    /**
     * Класс view holder для записи "задача".
     * Создаваемый объект выполняет функцию содержания необходимой информации для recycler view.
     */
    class TaskEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfTask: TextView = itemView.findViewById(R.id.textViewTaskName)
        private val creatorOfTask: TextView = itemView.findViewById(R.id.textViewTaskCreator)

        fun bind(taskEntry: TaskEntry) {
            nameOfTask.text = taskEntry.nameOfTask
            creatorOfTask.text = taskEntry.creatorOfTask
        }
    }
}