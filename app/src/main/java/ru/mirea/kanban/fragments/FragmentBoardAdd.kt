package ru.mirea.kanban.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.room.kanbanList.DBClientKanbanList
import ru.mirea.kanban.room.kanbanList.EntityKanbanList
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser

/**
 * Фрагмент интерфейса "Добавление списка".
 * Воспроизводит layout с полем наименования нового списка и кнопкой добавления такового.
 */
class FragmentBoardAdd : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.buttonBoardAdd).setOnClickListener {
            val listName = view.findViewById<TextInputLayout>(R.id.textInputLayoutBoardAddName).editText?.text.toString()
            val dbClient = DBClientKanbanList(requireActivity())
            lifecycleScope.launch {
                dbClient.insertAll(
                    EntityKanbanList(listName)
                )
            }
            dbClient.awaitResult()

            lifecycleScope.launch {
                dbClient.findByName(listName)
            }
            val newCurrentList = dbClient.awaitResult() as EntityKanbanList

            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putInt("currentListID", newCurrentList.id)
                apply()
            }

            view.findNavController().popBackStack()
        }
    }
}