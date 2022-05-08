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
 * Фрагмент интерфейса "Авторизация".
 * Воспроизводит layout с полями ввода имени пользователя и пароля, а также кнопками входа и регистрации.
 */
class FragmentAuthorization : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.buttonAuthAuthorize).setOnClickListener {
            it.findNavController().navigate(
                FragmentAuthorizationDirections.actionFragmentAuthorizationToFragmentBoard()
            )
        }

        view.findViewById<Button>(R.id.buttonAuthRegister).setOnClickListener {
            it.findNavController().navigate(
                FragmentAuthorizationDirections.actionFragmentAuthorizationToFragmentRegistration()
            )
        }
    }
}