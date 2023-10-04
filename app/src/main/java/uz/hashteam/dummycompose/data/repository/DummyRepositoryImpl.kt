package uz.hashteam.dummycompose.data.repository

import com.raghav.spacedawnv2.data.util.NetworkConnectivityManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import uz.hashteam.dummycompose.data.remote.DummyApi
import uz.hashteam.dummycompose.data.util.networkBoundResource
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.domain.repository.DummyRepository
import uz.hashteam.dummycompose.domain.util.Resource
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyApi: DummyApi,
    private val connectivityManager: NetworkConnectivityManager
): DummyRepository {
    override fun getAll(): Flow<Resource<List<Employee>>> = networkBoundResource(
        query = {
                flowOf()
        },
        fetch = { dummyApi.getAll() },
        saveFetchedResult = {},
        shouldFetch = { connectivityManager.isConnectedToNetwork() }
    )

    override fun getById(id: Int): Flow<Resource<Employee>> = networkBoundResource(
        query = {
            flowOf()
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