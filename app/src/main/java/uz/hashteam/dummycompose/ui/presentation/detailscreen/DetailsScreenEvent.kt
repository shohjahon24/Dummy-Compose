package uz.hashteam.dummycompose.ui.presentation.detailscreen

sealed class DetailsScreenEvent(
    val infoMessage: String? = null
) {
    data class Get(val id: Int) : DetailsScreenEvent()
}