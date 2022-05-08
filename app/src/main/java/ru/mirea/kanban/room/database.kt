package ru.mirea.kanban.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mirea.kanban.room.kanbanList.DaoKanbanList
import ru.mirea.kanban.room.kanbanList.EntityKanbanList
import ru.mirea.kanban.room.kanbanTask.DaoKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.DaoUser
import ru.mirea.kanban.room.user.EntityUser

@Database(entities = [EntityKanbanList::class, EntityKanbanTask::class, EntityUser::class], version = 1)
abstract class KanbanDatabase : RoomDatabase() {
    abstract fun daoKanbanList(): DaoKanbanList
    abstract fun daoKanbanTask(): DaoKanbanTask
    abstract fun daoUser(): DaoUser
}
