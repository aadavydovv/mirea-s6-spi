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
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

// TODO: fix literals
