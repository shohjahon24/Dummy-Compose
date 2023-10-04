package uz.hashteam.dummycompose.ui.presentation.employeesscreen

import androidx.compose.runtime.Stable
import uz.hashteam.dummycompose.domain.model.Employee

@Stable
data class EmployeesScreenState(
    val employees: MutableList<Employee> = mutableListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)