//package ru.mirea.kanban.room.kanbanTask
//
//import androidx.fragment.app.FragmentActivity
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import ru.mirea.kanban.MainActivity
//import java.lang.Thread.sleep
//
//class DBClientKanbanTask(mainActivity: FragmentActivity) {
//
//    private var result: Any? = null
//    private val db = (mainActivity as MainActivity).db
//
//    fun awaitResult(): Any? {
//        while (result == null) {
//            sleep(16)
//            // TODO: awful
//        }
//        val tempResult = result
//        result = null
//        return tempResult
//    }
//
//    suspend fun getAll() = withContext(Dispatchers.IO) {
//        result = db.daoKanbanTask().getAll()
//    }
//
//    suspend fun loadAllByIds(entityKanbanTaskIDs: IntArray) = withContext(Dispatchers.IO) {
//        result = db.daoKanbanTask().loadAllByIds(entityKanbanTaskIDs)
//    }
//
//    suspend fun findByName(name: String, type: String) = withContext(Dispatchers.IO) {
//        result = db.daoKanbanTask().findByName(name, type)
//    }
//
//    suspend fun insertAll(vararg entitiesKanbanTask: EntityKanbanTask) = withContext(Dispatchers.IO) {
//        db.daoKanbanTask().insertAll(*entitiesKanbanTask)
//        result = true
//    }
//
//    suspend fun delete(entityKanbanTask: EntityKanbanTask) = withContext(Dispatchers.IO) {
//        db.daoKanbanTask().delete(entityKanbanTask)
//        result = true
//    }
//
//    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
//        val targetEntity = db.daoKanbanTask().findByID(id)
//        db.daoKanbanTask().delete(targetEntity)
//        result = true
//    }
//
//    suspend fun update(entityKanbanTask: EntityKanbanTask) = withContext(Dispatchers.IO) {
//        db.daoKanbanTask().update(entityKanbanTask)
//        result = true
//    }
//
//    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
//        result = db.daoKanbanTask().findByID(id)
//    }
//}