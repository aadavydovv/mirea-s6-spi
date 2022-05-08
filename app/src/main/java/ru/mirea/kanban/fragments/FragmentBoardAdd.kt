package ru.mirea.kanban.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.mirea.kanban.R

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
}