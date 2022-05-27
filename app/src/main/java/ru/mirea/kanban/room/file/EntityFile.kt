package ru.mirea.kanban.room.file

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityFile(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "taskID") var taskID: Int,
    @ColumnInfo(name = "path") var path: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
