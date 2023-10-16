package uz.hashteam.dummycompose.ui.presentation.detailscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.hashteam.dummycompose.ui.presentation.detailscreen.components.DetailsScreenItem
import uz.hashteam.dummycompose.ui.theme.spacing

@SuppressLint("InlinedApi")
@Composable
fun DetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    userId: Int,
    viewModel: DetailsViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) { viewModel.onTriggerEvent(DetailsScreenEvent.Get(userId)) }
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = MaterialTheme.spacing.small)
        ) {

            (state.employee)?.let { it ->
                DetailsScreenItem(employee = it, onRemove = {
                    viewModel.onTriggerEvent(DetailsScreenEvent.Remove(it))
                    navController.popBackStack()
                }, onEdit = {
                    viewModel.onTriggerEvent(DetailsScreenEvent.Update(it))
                    navController.popBackStack()
                })
            }

        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}
