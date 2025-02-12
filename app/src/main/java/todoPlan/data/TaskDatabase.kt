package todoPlan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import todoPlan.data.dao.CategoryDao
import todoPlan.data.dao.TaskDao
import todoPlan.data.entity.Category
import todoPlan.data.entity.Task

@Database(entities = [Task::class, Category::class], version=1)
@TypeConverters(Converters::class)
abstract class TaskDatabase(): RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var Instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TaskDatabase::class.java, "task_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}