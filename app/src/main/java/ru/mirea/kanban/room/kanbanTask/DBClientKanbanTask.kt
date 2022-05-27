package ru.mirea.kanban.room.kanbanTask

import android.util.Log
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mirea.kanban.MainActivity
import java.lang.Thread.sleep

/**
 * Клиент запросов к базе данных задач доски.
 * Содержит методы обращения к базе данных.
 *
 * @param mainActivity главное activity приложения.
 * @property result результат отдельного запроса.
 * @property db база данных задач доски.
 */
class DBClientKanbanTask(mainActivity: FragmentActivity) {

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
     * Метод получения всех задач из базы данных.
     */
    suspend fun getAll() = withContext(Dispatchers.IO) {
        result = db.daoKanbanTask().getAll()
    }

    /**
     * Метод получения множества задач по идентификаторам.
     *
     * @param entityKanbanTaskIDs множество идентификаторов задач.
     */
    suspend fun loadAllByIDs(entityKanbanTaskIDs: IntArray) = withContext(Dispatchers.IO) {
        result = db.daoKanbanTask().loadAllByIDs(entityKanbanTaskIDs)
    }

    /**
     * Метод получения задачи по её наименованию.
     *
     * @param name наименование задачи.
     */
    suspend fun findByName(name: String) = withContext(Dispatchers.IO) {
        result = db.daoKanbanTask().findByName(name)
    }

    /**
     * Метод добавления множества задач в базу данных.
     *
     * @param entitiesKanbanTask множество задач.
     */
    suspend fun insertAll(vararg entitiesKanbanTask: EntityKanbanTask) = withContext(Dispatchers.IO) {
        db.daoKanbanTask().insertAll(*entitiesKanbanTask)
        result = true
    }

    /**
     * Метод удаления задачи из базы данных.
     *
     * @param entityKanbanTask задача для удаления.
     */
    suspend fun delete(entityKanbanTask: EntityKanbanTask) = withContext(Dispatchers.IO) {
        db.daoKanbanTask().delete(entityKanbanTask)
        result = true
    }

    /**
     * Метод удаления задачи по её идентификатору.
     *
     * @param id идентификатор задачи для удаления.
     */
    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        val targetEntity = db.daoKanbanTask().findByID(id)
        db.daoKanbanTask().delete(targetEntity)
        result = true
    }

    /**
     * Метод изменения (обновления) задачи.
     *
     * @param entityKanbanTask задача для изменения.
     */
    suspend fun update(entityKanbanTask: EntityKanbanTask) = withContext(Dispatchers.IO) {
        db.daoKanbanTask().update(entityKanbanTask)
        result = true
    }

    /**
     * Метод получения задачи по её идентификатору.
     *
     * @param id идентификатор задачи для получения.
     */
    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
        result = db.daoKanbanTask().findByID(id)
    }

    suspend fun getByListID(listID: Int) = withContext(Dispatchers.IO) {
        result = db.daoKanbanTask().getByListID(listID)
    }
}