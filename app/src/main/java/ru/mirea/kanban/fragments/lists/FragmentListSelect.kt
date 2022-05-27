package ru.mirea.kanban.fragments.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.fragments.FragmentTaskArgs
import ru.mirea.kanban.fragments.users.UserListAdapter
import ru.mirea.kanban.room.kanbanList.DBClientKanbanList
import ru.mirea.kanban.room.kanbanList.EntityKanbanList
import ru.mirea.kanban.room.kanbanTask.DBClientKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser

class FragmentListSelect : Fragment() {

    private val args: FragmentListSelectArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbClient = DBClientKanbanList(requireActivity())
        lifecycleScope.launch {
            dbClient.getAll()
        }

        val listEntries = dbClient.awaitResult() as List<EntityKanbanList>

        val recycler: RecyclerView = view.findViewById(R.id.recycler_lists)
        val adapter = ListsListAdapter(listEntries, requireActivity(), args.taskID)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

    }
}