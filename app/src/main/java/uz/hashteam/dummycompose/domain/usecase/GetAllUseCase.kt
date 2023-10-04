package uz.hashteam.dummycompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.domain.repository.DummyRepository
import uz.hashteam.dummycompose.domain.util.Resource
import javax.inject.Inject

class GetAllUseCase @Inject constructor(private val repository: DummyRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Employee>>> {
        return repository.getAll()
    }

}