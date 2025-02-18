package todoPlan.ui.today

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoplan.R
import kotlinx.coroutines.launch
import todoPlan.data.TaskDatabase
import todoPlan.data.entity.Task
import todoPlan.data.repository.CategoryRepository
import todoPlan.data.repository.TaskRepository
import todoPlan.ui.viewmodel.TaskViewModel
import todoPlan.ui.viewmodel.TaskViewModelFactory
import java.time.LocalDate
import java.time.LocalTime

class TodayFragment: Fragment() {
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(
            TaskRepository(TaskDatabase.getDatabase(requireContext()).taskDao()),
            CategoryRepository(TaskDatabase.getDatabase(requireContext()).categoryDao())
        )
    }
    //значение для тестов
    val newTask = Task(
        title = "New Task",
        date = LocalDate.now(),
        categoryId = null,
        startTime = LocalTime.now(),
        durationMinutes = 60,
        isCompleted = false)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_today, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.tasksRecyclerView)
        val taskAdapter = TaskAdapter(emptyList()) // create TaskAdapter with empty list
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // or any other layout manager
        viewModel.addTask(newTask)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                // update RecyclerView
                taskAdapter.setTasks(tasks)
            }
        }
    }
}