package ru.mirea.kanban.room.file

import android.util.Log
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mirea.kanban.MainActivity
import java.lang.Thread.sleep


class DBClientFile(mainActivity: FragmentActivity) {

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
                Log.d("oof", "counter error: file")
                break
            }
        }

        val tempResult = result
        result = null
        return tempResult
    }

    suspend fun getByTaskID(taskID: Int) = withContext(Dispatchers.IO) {
        result = db.daoFile().getByTaskID(taskID)
    }

    suspend fun insertAll(vararg entitiesFile: EntityFile) = withContext(Dispatchers.IO) {
        db.daoFile().insertAll(*entitiesFile)
        result = true
    }

    suspend fun delete(entityFile: EntityFile) = withContext(Dispatchers.IO) {
        db.daoFile().delete(entityFile)
        result = true
    }

    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        val targetEntity = db.daoFile().getByID(id)
        db.daoFile().delete(targetEntity)
        result = true
    }

    suspend fun update(entityFile: EntityFile) = withContext(Dispatchers.IO) {
        db.daoFile().update(entityFile)
        result = true
    }

    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
        result = db.daoFile().getByID(id)
    }
}