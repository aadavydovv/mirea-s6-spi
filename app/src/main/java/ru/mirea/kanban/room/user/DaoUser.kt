package ru.mirea.kanban.room.user

import androidx.room.*

@Dao
interface DaoUser {
//    @Query("SELECT * FROM EntityUser")
//    fun getAll(): List<EntityUser>
//
//    @Query("SELECT * FROM EntityUser WHERE id IN (:entityUserIDs)")
//    fun loadAllByIds(entityUserIDs: IntArray): List<EntityUser>
//
//    @Query("SELECT * FROM EntityUser WHERE name LIKE :name AND type LIKE :type LIMIT 1")
//    fun findByName(name: String, type: String): EntityUser
//
//    @Query("SELECT * FROM EntityUser WHERE id LIKE :id")
//    fun findByID(id: Int): EntityUser

    @Update
    fun update(entityUser: EntityUser)

    @Insert
    fun insertAll(vararg entitiesUser: EntityUser)

    @Delete
    fun delete(entityUser: EntityUser)
}
