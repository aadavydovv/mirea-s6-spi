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
 * Фрагмент интерфейса "Регистрация".
 * Воспроизводит layout с полями ввода имени и пароля, выбора роли нового пользователя,
 * а также кнопками регистрации и входа.
 */
class FragmentRegistration : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.buttonRegAuthorize).setOnClickListener {
            it.findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.buttonRegRegister).setOnClickListener {
            it.findNavController().navigate(
                FragmentRegistrationDirections.actionFragmentRegistrationToFragmentBoard()
            )
        }
    }
}