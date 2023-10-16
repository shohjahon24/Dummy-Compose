package uz.hashteam.dummycompose.ui.presentation.detailscreen

import uz.hashteam.dummycompose.domain.model.Employee

sealed class DetailsScreenEvent(
    val infoMessage: String? = null
) {
    data class Get(val id: Int) : DetailsScreenEvent()
    data class Remove(val id: Int) : DetailsScreenEvent()
    data class Update(val employee: Employee) : DetailsScreenEvent()
}