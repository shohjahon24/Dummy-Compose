package uz.hashteam.dummycompose.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.hashteam.dummycompose.data.local.Convertors
import uz.hashteam.dummycompose.data.local.DummyDao
import uz.hashteam.dummycompose.data.local.DummyDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideLaunchesDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): DummyDatabase {
       // val typeConverter = Convertors(moshi)
        return Room.databaseBuilder(
            context,
            DummyDatabase::class.java,
            "dummy.db"
        )/*.addTypeConverter(typeConverter)*/.build()
    }

    @Singleton
    @Provides
    fun provideDummyDao(database: DummyDatabase) = database.getEmployeeDao()

}
