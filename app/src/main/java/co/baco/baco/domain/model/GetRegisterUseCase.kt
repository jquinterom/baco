package co.baco.baco.domain.model

import android.util.Log
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.data.repository.RegisterRepository
import javax.inject.Inject

class GetRegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(): List<RegisterItem> {
        return repository.getAllRegistersFromDataBase()
    }
}