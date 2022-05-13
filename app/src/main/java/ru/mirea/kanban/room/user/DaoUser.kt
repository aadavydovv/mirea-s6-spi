package ru.mirea.kanban.room.user

import androidx.room.*

/**
 * DAO для пользователей.
 * Объявляет методы взаимодействия с базой данных пользователей.
 */
@Dao
interface DaoUser {
    /**
     * Метод получения всех пользователей.
     */
    @Query("SELECT * FROM EntityUser")
    fun getAll(): List<EntityUser>

    /**
     * Метод получения всех пользователей по идентификаторам.
     */
    @Query("SELECT * FROM EntityUser WHERE id IN (:entityUserIDs)")
    fun loadAllByIDs(entityUserIDs: IntArray): List<EntityUser>

    /**
     * Метод нахождения пользователя по имени.
     */
    @Query("SELECT * FROM EntityUser WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): EntityUser

    /**
     * Метод получения пользователя по идентификатору.
     */
    @Query("SELECT * FROM EntityUser WHERE id LIKE :id")
    fun findByID(id: Int): EntityUser

    /**
     * Метод изменения (обновления) пользователя.
     */
    @Update
    fun update(entityUser: EntityUser)

    /**
     * Метод добавления множества пользователей.
     */
    @Insert
    fun insertAll(vararg entitiesUser: EntityUser)

    /**
     * Метод удаления пользователя.
     */
    @Delete
    fun delete(entityUser: EntityUser)
}