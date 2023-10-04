package uz.hashteam.dummycompose.ui.presentation.employeesscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EmployeesScreen(
    modifier: Modifier = Modifier,
    viewModel: EmployeesViewModel = hiltViewModel(),
    systemBackButtonClicked: () -> Unit,
    onRemove: (Int) -> Unit,
    onEdit: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
}