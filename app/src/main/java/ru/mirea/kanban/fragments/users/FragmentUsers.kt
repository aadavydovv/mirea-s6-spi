package ru.mirea.kanban.fragments.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.kanban.R

/**
 * Фрагмент интерфейса "Пользователи".
 * Воспроизводит layout со списком пользователей доски.
 */
class FragmentUsers : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userEntries = listOf(
            UserEntry(1, "Артём Давыдов", "Управляющий"),
            UserEntry(2, "Александр Борисов", "Сотрудник"),
            UserEntry(3, "Ярослав Никулин", "Сотрудник"),
        )

        val recycler: RecyclerView = view.findViewById(R.id.recycler_users)
        val adapter = UserListAdapter(userEntries)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}