package uz.hashteam.dummycompose.ui.presentation.employeesscreen

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
import uz.hashteam.dummycompose.domain.usecase.GetAllUseCase
import uz.hashteam.dummycompose.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getAllUseCase: GetAllUseCase
): ViewModel() {


    private val _uiState: MutableStateFlow<EmployeesScreenState> =
        MutableStateFlow(EmployeesScreenState())
    val uiState: StateFlow<EmployeesScreenState> = _uiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<EmployeesScreenEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        getEmployees()
    }

    private fun getEmployees() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getAllUseCase().collect { result ->
                when(result) {
                    is  Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.errorMessage,
                                employees = mutableListOf()
                            )
                        }
                    }
                    is  Resource.Success -> {
                        _uiState.update { it.copy(
                            employees = result.data?.toMutableList() ?: mutableListOf(),
                            isLoading = false,
                            errorMessage = null) }
                    }
                }
            }
        }
    }

}