package ru.mirea.kanban.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser

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
        val items = listOf("Управляющий", "Сотрудник")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextRegUserType).setAdapter(adapter)

        view.findViewById<Button>(R.id.buttonRegAuthorize).setOnClickListener {
            it.findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.buttonRegRegister).setOnClickListener {

            val username = view.findViewById<TextInputLayout>(R.id.textInputLayoutRegUsername).editText?.text.toString()
            val regType = view.findViewById<TextInputLayout>(R.id.textInputLayoutRegType).editText?.text.toString()
            val password = view.findViewById<TextInputLayout>(R.id.textInputLayoutRegPassword).editText?.text.toString()

            val dbClient = DBClientUser(requireActivity())
            lifecycleScope.launch {
                dbClient.insertAll(
                    EntityUser(username, regType, password)
                )
            }
            dbClient.awaitResult()

            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("currentUser", username)
                apply()
            }

            it.findNavController().navigate(
                FragmentRegistrationDirections.actionFragmentRegistrationToFragmentBoard()
            )
        }
    }
}