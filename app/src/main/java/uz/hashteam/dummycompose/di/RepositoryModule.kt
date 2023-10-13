package uz.hashteam.dummycompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.hashteam.dummycompose.data.local.DummyDao
import uz.hashteam.dummycompose.data.remote.DummyApi
import uz.hashteam.dummycompose.data.repository.DummyRepositoryImpl
import uz.hashteam.dummycompose.data.util.NetworkConnectivityManager
import uz.hashteam.dummycompose.domain.repository.DummyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDummyRepository(
        dummyApi: DummyApi,
        dummyDao: DummyDao,
        connectivityManager: NetworkConnectivityManager
    ): DummyRepository {
        return DummyRepositoryImpl(dummyApi, dummyDao,  connectivityManager)
    }

}
