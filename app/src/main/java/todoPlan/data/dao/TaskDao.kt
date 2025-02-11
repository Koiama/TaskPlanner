package todoPlan.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import todoPlan.data.entity.Task
import java.time.LocalDate

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task): Long

    @Delete
    suspend fun delete(task: Task): Int

    @Query("SELECT * FROM tasks WHERE date = :date ORDER BY startTime ASC")
    fun getTaskByData(date: LocalDate): Flow<List<Task>>

    @Update
    suspend fun update(task: Task)
}