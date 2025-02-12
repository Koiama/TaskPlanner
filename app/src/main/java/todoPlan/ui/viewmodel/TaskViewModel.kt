package todoPlan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import todoPlan.data.entity.Category
import todoPlan.data.entity.Task
import todoPlan.data.repository.CategoryRepository
import todoPlan.data.repository.TaskRepository
import java.time.LocalDate

class TaskViewModel(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    init{
        viewModelScope.launch {
            taskRepository.getTasksByDate(LocalDate.now()).collect { tasks ->
                _tasks.value = tasks
            }
        }

        viewModelScope.launch {
            categoryRepository.getAllCategories().collect { categories ->
                _categories.value = categories
            }
        }
    }

    fun addTask(task: Task) = viewModelScope.launch {
        taskRepository.insertTask(task)
    }

    fun shiftTasks(updateTask: Task) = viewModelScope.launch {
        taskRepository.updateTask(updateTask)
    }
}