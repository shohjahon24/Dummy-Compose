package uz.hashteam.dummycompose.ui.presentation.employeesscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import uz.hashteam.dummycompose.R
import uz.hashteam.dummycompose.ui.presentation.employeesscreen.components.EmployeesScreenItem
import uz.hashteam.dummycompose.ui.theme.spacing

@SuppressLint("InlinedApi")
@Composable
fun EmployeesScreen(
    modifier: Modifier = Modifier,
    viewModel: EmployeesViewModel = hiltViewModel(),
    systemBackButtonClicked: () -> Unit,
    navController: NavController
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle().value



    Box(modifier = modifier.fillMaxSize()) {
        if (state.employees.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MaterialTheme.spacing.small)
            ) {
                items(state.employees) { item ->
                    key(item.id) {
                        EmployeesScreenItem(employee = item,
                            onRemove = {
                                viewModel.onTriggerEvent(EmployeesScreenEvent.Remove(it))
                            },
                            onEdit = {
                                navController.navigate("detail/${it}")
                            },
                            onClick = {
                                navController.navigate("detail/${it}")
                            }
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                    }
                }
            }
        } else {
            if (state.errorMessage.isNullOrEmpty().not()) {
                Text(
                    text = state.errorMessage!!,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(MaterialTheme.spacing.medium)
                )
            }
            if (state.isLoading.not()) {
                Text(
                    text = stringResource(id = R.string.no_employees),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    BackHandler {
        systemBackButtonClicked()
    }

}
