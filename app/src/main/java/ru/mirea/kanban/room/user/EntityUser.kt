package ru.mirea.kanban.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityUser(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "password") var password: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

// TODO: fix literals
