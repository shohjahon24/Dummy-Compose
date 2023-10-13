package uz.hashteam.dummycompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.domain.repository.DummyRepository
import uz.hashteam.dummycompose.domain.util.Resource
import javax.inject.Inject

class GetByIdUseCase @Inject constructor(private val repository: DummyRepository) {

    suspend operator fun invoke(id: Int): Flow<Resource<Employee>> {
        return repository.getById(id)
    }

}