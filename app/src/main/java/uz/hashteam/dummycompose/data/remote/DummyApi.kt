package uz.hashteam.dummycompose.data.remote

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.hashteam.dummycompose.data.remote.dto.EmployeeDto
import uz.hashteam.dummycompose.data.remote.dto.EmployeesDto

interface DummyApi {

    @GET("employees")
    suspend fun getAll(): Response<EmployeesDto>

    @GET("employee/{id}")
    suspend fun getById(@Path("id") id: Int): Response<EmployeeDto>

    @POST("create")
    suspend fun create(employeeDto: EmployeeDto)

    @PUT("update/{id}")
    suspend fun update(@Path("id") id: Int)

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: Int)


}
