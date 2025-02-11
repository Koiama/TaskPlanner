package todoPlan.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val date: LocalDate,
    val name: String,
    val startTime: LocalTime,
    val durationMinutes: Int,
    val isCompleted: Boolean = false
)