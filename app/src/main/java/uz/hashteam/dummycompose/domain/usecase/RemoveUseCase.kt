package uz.hashteam.dummycompose.domain.usecase

import uz.hashteam.dummycompose.domain.repository.DummyRepository
import javax.inject.Inject

class RemoveUseCase @Inject constructor(private val repository: DummyRepository) {

    suspend operator fun invoke(id: Int) {
        return repository.delete(id)
    }

}