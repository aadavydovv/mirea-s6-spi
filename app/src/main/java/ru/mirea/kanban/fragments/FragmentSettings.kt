package ru.mirea.kanban.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.mirea.kanban.R

/**
 * Фрагмент интерфейса "Настройки".
 * Воспроизводит layout с полями и кнопками изменения имени пользователя и пароля,
 * неизменяемым полем роли авторизованного пользователя и кнопкой смены пользователя.
 */
class FragmentSettings : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}