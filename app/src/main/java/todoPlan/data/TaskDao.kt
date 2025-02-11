package todoPlan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task): Long

    @Delete
    suspend fun delete(task: Task): Int

    @Query("SELECT * FROM tasks WHERE date = :date ORDER BY startTime ASC")
    fun getTaskByData(date: LocalDate): Flow<List<Task>>
}