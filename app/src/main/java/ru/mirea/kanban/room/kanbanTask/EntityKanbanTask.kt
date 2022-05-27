package ru.mirea.kanban.room.kanbanTask

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс данных - сущность "задача канбан".
 *
 * @param name наименование задачи.
 * @param creator создатель задачи.
 * @param id идентификатор задачи.
 */
@Entity
data class EntityKanbanTask(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "creator") var creator: String,
    @ColumnInfo(name = "listID") var listID: Int,
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "deadline") var deadline: String = "",
    @ColumnInfo(name = "workerID") var workerID: Int = -1,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

// TODO: fix literals
