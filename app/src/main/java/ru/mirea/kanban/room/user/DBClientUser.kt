//package ru.mirea.kanban.room.user
//
//import androidx.fragment.app.FragmentActivity
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import ru.mirea.kanban.MainActivity
//import java.lang.Thread.sleep
//
//class DBClientUser(mainActivity: FragmentActivity) {
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
//        result = db.daoUser().getAll()
//    }
//
//    suspend fun loadAllByIds(entityUserIDs: IntArray) = withContext(Dispatchers.IO) {
//        result = db.daoUser().loadAllByIds(entityUserIDs)
//    }
//
//    suspend fun findByName(name: String, type: String) = withContext(Dispatchers.IO) {
//        result = db.daoUser().findByName(name, type)
//    }
//
//    suspend fun insertAll(vararg entitiesUser: EntityUser) = withContext(Dispatchers.IO) {
//        db.daoUser().insertAll(*entitiesUser)
//        result = true
//    }
//
//    suspend fun delete(entityUser: EntityUser) = withContext(Dispatchers.IO) {
//        db.daoUser().delete(entityUser)
//        result = true
//    }
//
//    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
//        val targetEntity = db.daoUser().findByID(id)
//        db.daoUser().delete(targetEntity)
//        result = true
//    }
//
//    suspend fun update(entityUser: EntityUser) = withContext(Dispatchers.IO) {
//        db.daoUser().update(entityUser)
//        result = true
//    }
//
//    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
//        result = db.daoUser().findByID(id)
//    }
//}