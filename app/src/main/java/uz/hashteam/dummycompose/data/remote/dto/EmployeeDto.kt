package uz.hashteam.dummycompose.data.remote.dto
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json
import uz.hashteam.dummycompose.domain.model.Employee

@JsonClass(generateAdapter = true)
data class EmployeeDto(
    @Json(name = "employee_age")
    val employeeAge: Int? = 0,
    @Json(name = "employee_name")
    val employeeName: String? = "",
    @Json(name = "employee_salary")
    val employeeSalary: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "profile_image")
    val profileImage: String? = ""
)
    fun EmployeeDto.toDomain(): Employee {
        return Employee(
            id = this.id,
            age = this.employeeAge,
            name = this.employeeName,
            salary = this.employeeSalary,
            image = this.profileImage
        )
}