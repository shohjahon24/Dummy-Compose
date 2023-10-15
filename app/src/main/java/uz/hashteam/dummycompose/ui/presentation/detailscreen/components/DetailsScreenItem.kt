package uz.hashteam.dummycompose.ui.presentation.detailscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.ui.commoncomponent.CircularImage
import uz.hashteam.dummycompose.ui.theme.colors

@Composable
fun DetailsScreenItem(
    employee: Employee, onRemove: (Int) -> Unit, onEdit: (Int) -> Unit, onClick: (Int) -> Unit
) {

    var newEmployee: Employee by rememberSaveable { mutableStateOf(employee) }
    Column(modifier = Modifier.fillMaxSize()) {
        employee.image?.let { CircularImage(imageUrl = it, widthFraction = 0.35f) }
        OutlinedTextField(
            value = employee.name.orEmpty(),
            onValueChange = {
                newEmployee = employee.copy(name = it)
            },
        )
        Text(
            text = "Salary:" + employee.salary.toString(),
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Age:" + employee.age.toString(),
            color = MaterialTheme.colors.Blue,
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleLarge
        )

    }
}
