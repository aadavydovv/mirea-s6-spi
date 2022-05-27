package ru.mirea.kanban.room.comment

import android.util.Log
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mirea.kanban.MainActivity
import java.lang.Thread.sleep


class DBClientComment(mainActivity: FragmentActivity) {

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
                Log.d("oof", "counter error: comment")
                break
            }
        }

        val tempResult = result
        result = null
        return tempResult
    }

    suspend fun getByTaskID(taskID: Int) = withContext(Dispatchers.IO) {
        result = db.daoComment().getByTaskID(taskID)
    }

    suspend fun insertAll(vararg entitiesComment: EntityComment) = withContext(Dispatchers.IO) {
        db.daoComment().insertAll(*entitiesComment)
        result = true
    }

    suspend fun delete(entityComment: EntityComment) = withContext(Dispatchers.IO) {
        db.daoComment().delete(entityComment)
        result = true
    }

    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        val targetEntity = db.daoComment().getByID(id)
        db.daoComment().delete(targetEntity)
        result = true
    }

    suspend fun update(entityComment: EntityComment) = withContext(Dispatchers.IO) {
        db.daoComment().update(entityComment)
        result = true
    }

    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
        result = db.daoComment().getByID(id)
    }
}