package uz.hashteam.dummycompose.ui.presentation.detailscreen.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.ui.commoncomponent.CircularImage
import uz.hashteam.dummycompose.ui.theme.colors

@Composable
fun DetailsScreenItem(
    employee: Employee, onRemove: (Int) -> Unit, onEdit: (Employee) -> Unit
) {

    var newEmployee: Employee by rememberSaveable { mutableStateOf(employee) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        employee.image?.let { CircularImage(imageUrl = it, widthFraction = 0.35f, width = 100.dp, height = 100.dp) }
        OutlinedTextField(
            value = newEmployee.name.orEmpty(),
            label = { Text(text = "Name")},
            onValueChange = {
                newEmployee = employee.copy(name = it)
            },
        )
        OutlinedTextField(
            value = newEmployee.salary.toString(),
            label = { Text(text = "Salary")},
            onValueChange = {
                newEmployee = employee.copy(salary = it.toInt())
            },
        )
        OutlinedTextField(
            value = newEmployee.age.toString(),
            label = { Text(text = "Age")},
            onValueChange = {
                newEmployee = employee.copy(age = it.toInt())
            },
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { onEdit.invoke(employee) }, modifier = Modifier.width(120.dp), content = { Text(text = "Save", color = MaterialTheme.colorScheme.onPrimary)}, border = BorderStroke(1.dp, MaterialTheme.colors.Blue))
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { employee.id?.let { onRemove.invoke(it) } }, modifier = Modifier.width(120.dp), content = { Text(text = "Remove", color = MaterialTheme.colorScheme.onPrimary)}, border = BorderStroke(1.dp, MaterialTheme.colors.Blue))
        }
    }
}
