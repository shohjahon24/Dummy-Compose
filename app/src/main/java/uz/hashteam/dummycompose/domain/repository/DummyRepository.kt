package uz.hashteam.dummycompose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.domain.util.Resource

interface DummyRepository {

    fun getAll(): Flow<Resource<List<Employee>>>

    fun getById(id: Int): Flow<Resource<Employee>>

    suspend fun delete(id: Int)

    suspend fun update(employee: Employee)
}