package ru.mirea.kanban.fragments.users

/**
 * Класс данных - запись "пользователь".
 *
 * @param idOfUser идентификатор пользователя.
 * @param nameOfUser имя пользователя.
 * @param roleOfUser роль пользователя.
 */
data class UserEntry(
    val idOfUser: Int,
    val nameOfUser: String,
    val roleOfUser: String
)