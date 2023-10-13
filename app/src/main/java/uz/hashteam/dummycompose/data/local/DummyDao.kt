package uz.hashteam.dummycompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.hashteam.dummycompose.domain.model.Employee

@Dao
interface DummyDao {

    @Query("SELECT * FROM employee")
    fun getEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employee where id = :id")
    fun getEmployee(id: Int): Flow<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheEmployees(launches: List<Employee>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmployee(employee: Employee)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun removeEmployee(employee: Employee)


}
