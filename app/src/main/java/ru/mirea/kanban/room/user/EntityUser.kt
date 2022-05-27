package ru.mirea.kanban.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс данных - сущность "пользователь".
 *
 * @param name имя пользователя.
 * @param password пароль пользователя.
 * @param id идентификатор пользователя.
 */
@Entity
data class EntityUser(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "role") var role: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "notifyTaskID") var notifyTaskID: Int = -1,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

// TODO: fix literals
