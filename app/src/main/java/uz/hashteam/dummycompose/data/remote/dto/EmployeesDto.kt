package uz.hashteam.dummycompose.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EmployeesDto(
    @Json(name = "data")
    val `data`: List<EmployeeDto?>? = listOf(),
    @Json(name = "message")
    val message: String? = "",
    @Json(name = "status")
    val status: String? = ""
)