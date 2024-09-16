package co.baco.baco.data.repository

import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.common.entities.toDataBase
import co.baco.baco.data.database.dao.RegisterDao
import co.baco.baco.data.database.entities.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerDao: RegisterDao
) {
    suspend fun getAllRegistersFromDataBase(): List<RegisterItem> {
        return withContext(Dispatchers.IO) {
            val response = registerDao.getAll()
            response.map { it.toDomain() }
        }
    }

    suspend fun insertRegister(registerItem: RegisterItem) {
        withContext(Dispatchers.IO) {
            registerDao.insert(registerItem.toDataBase())
        }
    }
}