package ru.mirea.kanban.room.kanbanList

import androidx.room.*

/**
 * DAO для списков.
 * Объявляет методы взаимодействия с базой данных списков.
 */
@Dao
interface DaoKanbanList {
    /**
     * Метод получения всех списков.
     */
    @Query("SELECT * FROM EntityKanbanList")
    fun getAll(): List<EntityKanbanList>

    /**
     * Метод получения всех списков по идентификаторам.
     */
    @Query("SELECT * FROM EntityKanbanList WHERE id IN (:entityKanbanListIDs)")
    fun loadAllByIDs(entityKanbanListIDs: IntArray): List<EntityKanbanList>

    /**
     * Метод нахождения списка по наименованию.
     */
    @Query("SELECT * FROM EntityKanbanList WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): EntityKanbanList

    /**
     * Метод получения списка по идентификатору.
     */
    @Query("SELECT * FROM EntityKanbanList WHERE id LIKE :id")
    fun findByID(id: Int): EntityKanbanList

    /**
     * Метод изменения (обновления) списка.
     */
    @Update
    fun update(entityKanbanList: EntityKanbanList)

    /**
     * Метод добавления множества списков.
     */
    @Insert
    fun insertAll(vararg entitiesKanbanList: EntityKanbanList)

    /**
     * Метод удаления списка.
     */
    @Delete
    fun delete(entityKanbanList: EntityKanbanList)
}