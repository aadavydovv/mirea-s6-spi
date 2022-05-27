package ru.mirea.kanban.room.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityComment(
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "taskID") var taskID: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
