package uz.hashteam.dummycompose.ui.presentation.detailscreen

import androidx.compose.runtime.Stable
import uz.hashteam.dummycompose.domain.model.Employee

@Stable
data class DetailsScreenState(
    val employee: Employee? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)