package ru.mirea.kanban.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser

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
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val currentUser = sharedPref?.getString("currentUser", "oops")!!

        val dbClient = DBClientUser(requireActivity())
        lifecycleScope.launch {
            dbClient.findByName(currentUser)
        }

        val user = dbClient.awaitResult() as EntityUser

        view.findViewById<TextView>(R.id.textSettingsRoleValue).text = user.role
        view.findViewById<TextInputLayout>(R.id.textInputLayoutSettingsPassword).editText?.setText(user.password)
        view.findViewById<TextInputLayout>(R.id.textInputLayoutSettingsUsername).editText?.setText(currentUser)
    }
}