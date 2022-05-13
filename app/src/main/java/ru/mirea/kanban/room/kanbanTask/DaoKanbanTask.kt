package ru.mirea.kanban.room.kanbanTask

import androidx.room.*

/**
 * DAO для задач.
 * Объявляет методы взаимодействия с базой данных задач.
 */
@Dao
interface DaoKanbanTask {
    /**
     * Метод получения всех задач.
     */
    @Query("SELECT * FROM EntityKanbanTask")
    fun getAll(): List<EntityKanbanTask>

    /**
     * Метод получения всех задач по идентификаторам.
     */
    @Query("SELECT * FROM EntityKanbanTask WHERE id IN (:entityKanbanTaskIDs)")
    fun loadAllByIDs(entityKanbanTaskIDs: IntArray): List<EntityKanbanTask>

    /**
     * Метод нахождения задачи по наименованию.
     */
    @Query("SELECT * FROM EntityKanbanTask WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): EntityKanbanTask

    /**
     * Метод получения задачи по идентификатору.
     */
    @Query("SELECT * FROM EntityKanbanTask WHERE id LIKE :id")
    fun findByID(id: Int): EntityKanbanTask

    /**
     * Метод изменения (обновления) задачи.
     */
    @Update
    fun update(entityKanbanTask: EntityKanbanTask)

    /**
     * Метод добавления множества задач.
     */
    @Insert
    fun insertAll(vararg entitiesKanbanTask: EntityKanbanTask)

    /**
     * Метод удаления задачи.
     */
    @Delete
    fun delete(entityKanbanTask: EntityKanbanTask)
}
