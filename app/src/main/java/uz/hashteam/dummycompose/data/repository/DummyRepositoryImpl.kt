package uz.hashteam.dummycompose.data.repository

import kotlinx.coroutines.flow.Flow
import uz.hashteam.dummycompose.data.local.DummyDao
import uz.hashteam.dummycompose.data.remote.DummyApi
import uz.hashteam.dummycompose.data.remote.dto.toDomain
import uz.hashteam.dummycompose.data.util.NetworkConnectivityManager
import uz.hashteam.dummycompose.data.util.networkBoundResource
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.domain.repository.DummyRepository
import uz.hashteam.dummycompose.domain.util.Resource
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyApi: DummyApi,
    private val dummyDao: DummyDao,
    private val connectivityManager: NetworkConnectivityManager
) : DummyRepository {
    override fun getAll(): Flow<Resource<List<Employee>>> = networkBoundResource(
        query = { dummyDao.getEmployees() },
        fetch = { dummyApi.getAll() },
        saveFetchedResult = { response ->
            val employees = response.body()?.data?.filterNotNull() ?: emptyList()
            dummyDao.cacheEmployees(employees.map { it.toDomain() })
        },
        shouldFetch = { connectivityManager.isConnectedToNetwork() }
    )

    override fun getById(id: Int): Flow<Resource<Employee>> = networkBoundResource(
        query = {
            dummyDao.getEmployee(id)
        },
        fetch = { dummyApi.getById(id) },
        saveFetchedResult = {},
        shouldFetch = { connectivityManager.isConnectedToNetwork() }
    )

    override suspend fun delete(id: Int) {
        dummyApi.delete(id)
    }

    override suspend fun update(id: Int) {
        dummyApi.update(id)
    }

}