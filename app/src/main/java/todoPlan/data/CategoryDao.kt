package todoPlan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category): Long

    @Delete
    suspend fun delete(category: Category): Int

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>

}