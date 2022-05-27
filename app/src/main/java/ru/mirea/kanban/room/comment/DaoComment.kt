package ru.mirea.kanban.room.comment

import androidx.room.*

@Dao
interface DaoComment {

    @Query("SELECT * FROM EntityComment WHERE taskID LIKE :taskID")
    fun getByTaskID(taskID: Int): List<EntityComment>

    @Query("SELECT * FROM EntityComment WHERE id LIKE :id")
    fun getByID(id: Int): EntityComment

    @Update
    fun update(entityComment: EntityComment)

    @Insert
    fun insertAll(vararg entitiesComment: EntityComment)

    @Delete
    fun delete(entityComment: EntityComment)
}