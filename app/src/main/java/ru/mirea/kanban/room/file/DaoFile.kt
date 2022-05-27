package ru.mirea.kanban.room.file

import androidx.room.*

@Dao
interface DaoFile {

    @Query("SELECT * FROM EntityFile WHERE taskID LIKE :taskID")
    fun getByTaskID(taskID: Int): List<EntityFile>

    @Query("SELECT * FROM EntityFile WHERE id LIKE :id")
    fun getByID(id: Int): EntityFile

    @Update
    fun update(entityFile: EntityFile)

    @Insert
    fun insertAll(vararg entitiesFile: EntityFile)

    @Delete
    fun delete(entityFile: EntityFile)
}