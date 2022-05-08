package ru.mirea.kanban.room.kanbanList

import androidx.room.*

@Dao
interface DaoKanbanList {
    @Query("SELECT * FROM EntityKanbanList")
    fun getAll(): List<EntityKanbanList>

    @Query("SELECT * FROM EntityKanbanList WHERE id IN (:entityKanbanListIDs)")
    fun loadAllByIDs(entityKanbanListIDs: IntArray): List<EntityKanbanList>

    @Query("SELECT * FROM EntityKanbanList WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): EntityKanbanList

    @Query("SELECT * FROM EntityKanbanList WHERE id LIKE :id")
    fun findByID(id: Int): EntityKanbanList

    @Update
    fun update(entityKanbanList: EntityKanbanList)

    @Insert
    fun insertAll(vararg entitiesKanbanList: EntityKanbanList)

    @Delete
    fun delete(entityKanbanList: EntityKanbanList)
}
