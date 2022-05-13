package ru.mirea.kanban.fragments.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.kanban.R

/**
 * Адаптер списка пользователей для recycler view.
 * Предоставляет recycler view необходимую информацию из получаемого списка пользователей.
 */
class UserListAdapter(
    private val userEntries: List<UserEntry>,
) :
    RecyclerView.Adapter<UserListAdapter.UserEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_user_entry, parent, false)

        return UserEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserEntryViewHolder, position: Int) {
        holder.bind(userEntries[position])
    }

    override fun getItemCount() = userEntries.size

    /**
     * Класс view holder для записи "пользователь".
     * Создаваемый объект выполняет функцию содержания необходимой информации для recycler view.
     */
    class UserEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfUser: TextView = itemView.findViewById(R.id.textViewUserName)
        private val roleOfUser: TextView = itemView.findViewById(R.id.textViewUserRole)

        fun bind(userEntry: UserEntry) {
            nameOfUser.text = userEntry.nameOfUser
            roleOfUser.text = userEntry.roleOfUser
        }
    }
}