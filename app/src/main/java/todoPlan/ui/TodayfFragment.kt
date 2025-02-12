package todoPlan.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import todoPlan.data.TaskDatabase
import todoPlan.data.repository.CategoryRepository
import todoPlan.data.repository.TaskRepository
import todoPlan.ui.viewmodel.TaskViewModel
import todoPlan.ui.viewmodel.TaskViewModelFactory

class TodayFragment: Fragment() {
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(
            TaskRepository(TaskDatabase.getDatabase(requireContext()).taskDao()),
            CategoryRepository(TaskDatabase.getDatabase(requireContext()).categoryDao())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                // update RecyclerView
            }
        }
    }
}