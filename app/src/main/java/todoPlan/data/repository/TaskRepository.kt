package todoPlan.data.repository

import kotlinx.coroutines.flow.Flow
import todoPlan.data.dao.TaskDao
import todoPlan.data.entity.Task
import java.time.LocalDate

class TaskRepository (private val taskDao: TaskDao) {

    fun getTasksByDate(date: LocalDate): Flow<List<Task>> =
        taskDao.getTaskByData(date)

    suspend fun insertTask(task: Task) =
        taskDao.insert(task)

    suspend fun deleteTask(task: Task) =
        taskDao.delete(task)

    suspend fun updateTask(task: Task) =
        taskDao.update(task)
}