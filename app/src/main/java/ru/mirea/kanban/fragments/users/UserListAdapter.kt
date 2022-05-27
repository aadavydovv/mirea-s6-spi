package ru.mirea.kanban.fragments.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.mirea.kanban.MainActivity
import ru.mirea.kanban.R
import ru.mirea.kanban.room.kanbanTask.DBClientKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.EntityUser

/**
 * Адаптер списка пользователей для recycler view.
 * Предоставляет recycler view необходимую информацию из получаемого списка пользователей.
 */
class UserListAdapter(
    private val userEntries: List<EntityUser>, private val taskID: Int, private val mainActivity: FragmentActivity
) :
    RecyclerView.Adapter<UserListAdapter.UserEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_user_entry, parent, false)

        return UserEntryViewHolder(view, taskID, mainActivity)
    }

    override fun onBindViewHolder(holder: UserEntryViewHolder, position: Int) {
        holder.bind(userEntries[position])
    }

    override fun getItemCount() = userEntries.size

    /**
     * Класс view holder для записи "пользователь".
     * Создаваемый объект выполняет функцию содержания необходимой информации для recycler view.
     */
    class UserEntryViewHolder(itemView: View, private val taskID: Int, private val mainActivity: FragmentActivity) : RecyclerView.ViewHolder(itemView) {
        private val nameOfUser: TextView = itemView.findViewById(R.id.textViewUserName)
        private val roleOfUser: TextView = itemView.findViewById(R.id.textViewUserRole)

        fun bind(userEntry: EntityUser) {
            nameOfUser.text = userEntry.name
            roleOfUser.text = userEntry.role

            if (taskID != -1) {
                itemView.setOnClickListener {
                    val dbClient = DBClientKanbanTask(mainActivity)
                    mainActivity.lifecycleScope.launch {
                        dbClient.getByID(taskID)
                    }

                    val task = dbClient.awaitResult() as EntityKanbanTask
                    task.workerID = userEntry.id
                    mainActivity.lifecycleScope.launch {
                        dbClient.update(task)
                    }
                    dbClient.awaitResult()

                    it.findNavController().popBackStack()
                }
            }
        }
    }
}