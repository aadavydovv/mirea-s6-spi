package ru.mirea.kanban.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mirea.kanban.room.comment.DaoComment
import ru.mirea.kanban.room.comment.EntityComment
import ru.mirea.kanban.room.file.DaoFile
import ru.mirea.kanban.room.file.EntityFile
import ru.mirea.kanban.room.kanbanList.DaoKanbanList
import ru.mirea.kanban.room.kanbanList.EntityKanbanList
import ru.mirea.kanban.room.kanbanTask.DaoKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask
import ru.mirea.kanban.room.user.DaoUser
import ru.mirea.kanban.room.user.EntityUser

@Database(
    entities = [EntityKanbanList::class, EntityKanbanTask::class, EntityUser::class, EntityComment::class, EntityFile::class],
    version = 1
)
abstract class KanbanDatabase : RoomDatabase() {
    abstract fun daoKanbanList(): DaoKanbanList
    abstract fun daoKanbanTask(): DaoKanbanTask
    abstract fun daoUser(): DaoUser
    abstract fun daoComment(): DaoComment
    abstract fun daoFile(): DaoFile
}
