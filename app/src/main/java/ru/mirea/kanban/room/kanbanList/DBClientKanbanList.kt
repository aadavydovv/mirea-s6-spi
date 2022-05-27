package ru.mirea.kanban.room.kanbanList

import android.util.Log
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mirea.kanban.MainActivity
import java.lang.Thread.sleep

/**
 * Клиент запросов к базе данных списков доски.
 * Содержит методы обращения к базе данных.
 *
 * @param mainActivity главное activity приложения.
 * @property result результат отдельного запроса.
 * @property db база данных списков доски.
 */
class DBClientKanbanList(mainActivity: FragmentActivity) {

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

            if (counter > 18) {
                Log.d("oof", "counter error: list")
                break
            }
        }

        val tempResult = result
        result = null
        return tempResult
    }

    /**
     * Метод получения всех списков из базы данных.
     */
    suspend fun getAll() = withContext(Dispatchers.IO) {
        result = db.daoKanbanList().getAll()
    }

    /**
     * Метод получения множества списков по идентификаторам.
     *
     * @param entityKanbanListIDs множество идентификаторов списков.
     */
    suspend fun loadAllByIDs(entityKanbanListIDs: IntArray) = withContext(Dispatchers.IO) {
        result = db.daoKanbanList().loadAllByIDs(entityKanbanListIDs)
    }

    /**
     * Метод получения списка по его наименованию.
     *
     * @param name наименование списка.
     */
    suspend fun findByName(name: String) = withContext(Dispatchers.IO) {
        result = db.daoKanbanList().findByName(name)
    }

    /**
     * Метод добавления множества списков в базу данных.
     *
     * @param entitiesKanbanList множество списков.
     */
    suspend fun insertAll(vararg entitiesKanbanList: EntityKanbanList) = withContext(Dispatchers.IO) {
        db.daoKanbanList().insertAll(*entitiesKanbanList)
        result = true
    }

    /**
     * Метод удаления списка из базы данных.
     *
     * @param entityKanbanList список для удаления.
     */
    suspend fun delete(entityKanbanList: EntityKanbanList) = withContext(Dispatchers.IO) {
        db.daoKanbanList().delete(entityKanbanList)
        result = true
    }

    /**
     * Метод удаления списка по его идентификатору.
     *
     * @param id идентификатор списка для удаления.
     */
    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        val targetEntity = db.daoKanbanList().findByID(id)
        db.daoKanbanList().delete(targetEntity)
        result = true
    }

    /**
     * Метод изменения (обновления) списка.
     *
     * @param entityKanbanList список для изменения.
     */
    suspend fun update(entityKanbanList: EntityKanbanList) = withContext(Dispatchers.IO) {
        db.daoKanbanList().update(entityKanbanList)
        result = true
    }

    /**
     * Метод получения списка по его идентификатору.
     *
     * @param id идентификатор списка для получения.
     */
    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
        result = db.daoKanbanList().findByID(id)
    }
}