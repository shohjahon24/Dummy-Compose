package uz.hashteam.dummycompose.ui.presentation.employeesscreen

sealed class EmployeesScreenEvent(
    val infoMessage: String? = null
) {
    data object GetAll : EmployeesScreenEvent()
    data class Remove(val id: Int) : EmployeesScreenEvent()
}