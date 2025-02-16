package todoPlan.ui.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoplan.R
import todoPlan.data.entity.Task

class TaskAdapter(private var tasks: List<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskTitleTextView: TextView = itemView.findViewById(R.id.taskTitleTextView)
        // ... другие элементы, например, TextView для категории, CheckBox для выполнения и т.д.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        // Здесь нужно заполнить элементы разметки данными из объекта Task
        val currentTask = tasks[position]
        holder.taskTitleTextView.text = currentTask.title
        // ... заполнение других элементов разметки
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }
}