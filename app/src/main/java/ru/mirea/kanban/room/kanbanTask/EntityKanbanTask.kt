package ru.mirea.kanban.room.kanbanTask

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityKanbanTask(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "type") var type: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

// TODO: fix literals
