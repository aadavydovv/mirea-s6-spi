package ru.mirea.kanban.fragments.lists

import android.content.Context
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
import ru.mirea.kanban.room.kanbanList.DBClientKanbanList
import ru.mirea.kanban.room.kanbanList.EntityKanbanList
import ru.mirea.kanban.room.kanbanTask.DBClientKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.EntityUser

class ListsListAdapter(
    private val listEntries: List<EntityKanbanList>, private val mainActivity: FragmentActivity, private val taskID: Int
) :
    RecyclerView.Adapter<ListsListAdapter.ListEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_list_entry, parent, false)

        return ListEntryViewHolder(view, mainActivity, taskID)
    }

    override fun onBindViewHolder(holder: ListEntryViewHolder, position: Int) {
        holder.bind(listEntries[position])
    }

    override fun getItemCount() = listEntries.size

    class ListEntryViewHolder(itemView: View, private val mainActivity: FragmentActivity, private val taskID: Int) :
        RecyclerView.ViewHolder(itemView) {
        private val nameOfList: TextView = itemView.findViewById(R.id.textViewListName)

        fun bind(listEntry: EntityKanbanList) {
            itemView.setOnClickListener {
                val dbClientList = DBClientKanbanList(mainActivity)
                mainActivity.lifecycleScope.launch {
                    dbClientList.findByName(listEntry.name)
                }

                val newCurrentList = dbClientList.awaitResult() as EntityKanbanList

                val sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putInt("currentListID", newCurrentList.id)
                    apply()
                }

                if (taskID != -1) {
                    val dbClient = DBClientKanbanTask(mainActivity)
                    mainActivity.lifecycleScope.launch {
                        dbClient.getByID(taskID)
                    }
                    val task = dbClient.awaitResult() as EntityKanbanTask

                    task.listID = newCurrentList.id
                    mainActivity.lifecycleScope.launch {
                        dbClient.update(task)
                    }
                    dbClient.awaitResult()
                }

                itemView.findNavController().popBackStack()
            }

            nameOfList.text = listEntry.name
        }
    }
}