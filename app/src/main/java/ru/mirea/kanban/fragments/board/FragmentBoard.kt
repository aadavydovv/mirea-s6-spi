package ru.mirea.kanban.fragments.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.room.kanbanList.DBClientKanbanList
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask

/**
 * Фрагмент интерфейса "Доска".
 * Воспроизводит layout с отдельным списком задач, кнопками выбора и добавления списка,
 * кнопкой добавления задачи и кнопкой включения фильтра личных задач.
 */
class FragmentBoard : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view).visibility =
            View.VISIBLE

        view.findViewById<ExtendedFloatingActionButton>(R.id.fabBoardAddList).setOnClickListener {
            it.findNavController().navigate(
                FragmentBoardDirections.actionFragmentBoardToFragmentBoardAdd()
            )
        }

        view.findViewById<ExtendedFloatingActionButton>(R.id.fabBoardAddTask).setOnClickListener {
            it.findNavController().navigate(
                FragmentBoardDirections.actionFragmentBoardToFragmentTask()
            )
        }

//        val dbClient = DBClientKanbanTask(requireActivity())
//        lifecycleScope.launch {
//            dbClient.getAll()
//        }

//        val taskEntries = dbClient.awaitResult() as List<EntityKanbanTask>
        val taskEntries = listOf(
            TaskEntry(1, "Сформировать матрицу требований", "Давыдов Артём"),
            TaskEntry(2, "Описать функциональные требования", "Давыдов Артём"),
            TaskEntry(3, "Подготовить ТЗ", "Давыдов Артём"),
            TaskEntry(4, "Создать дизайн", "Давыдов Артём")
        )

        val recycler: RecyclerView = view.findViewById(R.id.recyclerBoardTasks)
        val adapter = TaskListAdapter(taskEntries)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<TextView>(R.id.textBoardName).text = "Активные"

        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}