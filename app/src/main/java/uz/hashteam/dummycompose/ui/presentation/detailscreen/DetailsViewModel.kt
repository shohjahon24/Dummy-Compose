package uz.hashteam.dummycompose.ui.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.domain.usecase.GetAllUseCase
import uz.hashteam.dummycompose.domain.usecase.GetByIdUseCase
import uz.hashteam.dummycompose.domain.usecase.RemoveUseCase
import uz.hashteam.dummycompose.domain.usecase.UpdateUseCase
import uz.hashteam.dummycompose.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getByIdUseCase: GetByIdUseCase,
    private val removeUseCase: RemoveUseCase,
    private val updateUseCase: UpdateUseCase,

    ): ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenState> =
        MutableStateFlow(DetailsScreenState())
    val uiState: StateFlow<DetailsScreenState> = _uiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<DetailsScreenEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onTriggerEvent(event: DetailsScreenEvent) {
        when(event) {
            is DetailsScreenEvent.Get -> getEmployee(event.id)
            is DetailsScreenEvent.Update -> update(event.employee)
            is DetailsScreenEvent.Remove -> remove(event.id)
        }
    }

    private fun getEmployee(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getByIdUseCase(id).collect { result ->
                when(result) {
                    is  Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.errorMessage,
                                employee = null
                            )
                        }
                    }
                    is  Resource.Success -> {
                        _uiState.update { it.copy(
                            employee = result.data,
                            isLoading = false,
                            errorMessage = null) }
                    }
                }
            }
        }
    }

    private fun remove(id: Int) {
        viewModelScope.launch {
          removeUseCase.invoke(id)
        }
    }

    private fun update(employee: Employee) {
        viewModelScope.launch {
            updateUseCase.invoke(employee)
        }
    }

}