package ru.mirea.kanban.room.kanbanTask

import androidx.room.*

@Dao
interface DaoKanbanTask {
//    @Query("SELECT * FROM EntityKanbanTask")
//    fun getAll(): List<EntityKanbanTask>
//
//    @Query("SELECT * FROM EntityKanbanTask WHERE id IN (:entityKanbanTaskIDs)")
//    fun loadAllByIds(entityKanbanTaskIDs: IntArray): List<EntityKanbanTask>
//
//    @Query("SELECT * FROM EntityKanbanTask WHERE name LIKE :name AND type LIKE :type LIMIT 1")
//    fun findByName(name: String, type: String): EntityKanbanTask
//
//    @Query("SELECT * FROM EntityKanbanTask WHERE id LIKE :id")
//    fun findByID(id: Int): EntityKanbanTask

    @Update
    fun update(entityKanbanTask: EntityKanbanTask)

    @Insert
    fun insertAll(vararg entitiesKanbanTask: EntityKanbanTask)

    @Delete
    fun delete(entityKanbanTask: EntityKanbanTask)
}
