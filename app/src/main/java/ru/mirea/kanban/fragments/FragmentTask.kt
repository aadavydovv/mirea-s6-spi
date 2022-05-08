package ru.mirea.kanban.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.mirea.kanban.R

/**
 * Фрагмент интерфейса "Задача".
 * Воспроизводит layout с полями наименования и описания задачи, а также кнопками: оставить комменатрий,
 * прикрепить файл, установить напоминание, установить срок выполнения, назначить пользователя,
 * оповестить о завершении. Некоторые кнопки доступны лишь для руководящей роли.
 */
class FragmentTask : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.buttonTaskBack).setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}