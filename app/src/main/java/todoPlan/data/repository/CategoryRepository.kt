package todoPlan.data.repository

import kotlinx.coroutines.flow.Flow
import todoPlan.data.dao.CategoryDao
import todoPlan.data.entity.Category

class CategoryRepository(private val categoryDao: CategoryDao) {

    fun getAllCategories(): Flow<List<Category>> =
        categoryDao.getAllCategories()

    suspend fun insertCategory (category: Category) =
        categoryDao.insert(category)

    suspend fun deleteCategory (category: Category) =
        categoryDao.delete(category)
}