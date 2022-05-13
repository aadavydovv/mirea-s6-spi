package ru.mirea.kanban.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.textSettingsRoleValue).text = "Управляющий"
        view.findViewById<TextInputLayout>(R.id.textInputLayoutSettingsPassword).editText?.setText("12345678")
        view.findViewById<TextInputLayout>(R.id.textInputLayoutSettingsUsername).editText?.setText("davydov")
    }
}