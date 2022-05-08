package ru.mirea.kanban.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.mirea.kanban.R

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
    }
}