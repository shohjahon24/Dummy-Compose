package uz.hashteam.dummycompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey
    val id: Int?,
    val age: Int?,
    val name: String?,
    val salary: Int?,
    val image: String?
)