package uz.hashteam.dummycompose.domain.usecase

import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.domain.repository.DummyRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val repository: DummyRepository) {

    suspend operator fun invoke(employee: Employee) {
        return repository.update(employee)
    }

}