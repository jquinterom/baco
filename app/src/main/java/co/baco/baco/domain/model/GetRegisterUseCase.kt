package co.baco.baco.domain.model

import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.data.repository.RegisterRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class GetRegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(): Flow<List<RegisterItem>> {
        return repository.getAllRegistersFromDataBase()
    }
}