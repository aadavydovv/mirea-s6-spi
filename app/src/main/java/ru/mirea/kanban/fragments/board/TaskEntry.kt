package ru.mirea.kanban.fragments.board

/**
 * Класс данных - запись "задача".
 *
 * @param idOfTask идентификатор задачи.
 * @param nameOfTask наименование задачи.
 * @param creatorOfTask создатель задачи.
 */
data class TaskEntry(
    val idOfTask: Int,
    val nameOfTask: String,
    val creatorOfTask: String
)