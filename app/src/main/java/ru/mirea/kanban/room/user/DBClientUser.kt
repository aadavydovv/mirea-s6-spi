package ru.mirea.kanban.room.user

import android.util.Log
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mirea.kanban.MainActivity
import java.lang.Thread.sleep

/**
 * Клиент запросов к базе данных пользователей доски.
 * Содержит методы обращения к базе данных.
 *
 * @param mainActivity главное activity приложения.
 * @property result результат отдельного запроса.
 * @property db база данных пользователей доски.
 */
class DBClientUser(mainActivity: FragmentActivity) {

    private var result: Any? = null
    private val db = (mainActivity as MainActivity).db

    /**
     * Метод ожидания и возврата результата запроса.
     *
     * @return результат запроса.
     */
    fun awaitResult(): Any? {
        var counter = 0
        while (result == null) {
            sleep(16)
            counter += 1

            if (counter > 187) {
                Log.d("oof", "counter error: task")
                break
            }
        }

        val tempResult = result
        result = null
        return tempResult
    }

    /**
     * Метод получения всех пользователей из базы данных.
     */
    suspend fun getAll() = withContext(Dispatchers.IO) {
        result = db.daoUser().getAll()
    }

    /**
     * Метод получения множества пользователей по идентификаторам.
     *
     * @param entityUserIDs множество идентификаторов пользователей.
     */
    suspend fun loadAllByIDs(entityUserIDs: IntArray) = withContext(Dispatchers.IO) {
        result = db.daoUser().loadAllByIDs(entityUserIDs)
    }

    /**
     * Метод получения пользователя по его наименованию.
     *
     * @param name наименование пользователя.
     */
    suspend fun findByName(name: String) = withContext(Dispatchers.IO) {
        result = db.daoUser().findByName(name)
    }

    /**
     * Метод добавления множества пользователей в базу данных.
     *
     * @param entitiesUser множество пользователей.
     */
    suspend fun insertAll(vararg entitiesUser: EntityUser) = withContext(Dispatchers.IO) {
        db.daoUser().insertAll(*entitiesUser)
        result = true
    }

    /**
     * Метод удаления пользователя из базы данных.
     *
     * @param entityUser пользователь для удаления.
     */
    suspend fun delete(entityUser: EntityUser) = withContext(Dispatchers.IO) {
        db.daoUser().delete(entityUser)
        result = true
    }

    /**
     * Метод удаления пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя для удаления.
     */
    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        val targetEntity = db.daoUser().findByID(id)
        db.daoUser().delete(targetEntity)
        result = true
    }

    /**
     * Метод изменения (обновления) пользователя.
     *
     * @param entityUser пользователь для изменения.
     */
    suspend fun update(entityUser: EntityUser) = withContext(Dispatchers.IO) {
        db.daoUser().update(entityUser)
        result = true
    }

    /**
     * Метод получения пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя для получения.
     */
    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
        result = db.daoUser().findByID(id)
    }
}