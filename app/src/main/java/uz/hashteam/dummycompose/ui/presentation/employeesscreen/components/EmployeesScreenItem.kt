package uz.hashteam.dummycompose.ui.presentation.employeesscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import uz.hashteam.dummycompose.domain.model.Employee
import uz.hashteam.dummycompose.ui.commoncomponent.CircularImage
import uz.hashteam.dummycompose.ui.theme.colors
import uz.hashteam.dummycompose.ui.theme.spacing

@Composable
fun EmployeesScreenItem(
    employee: Employee,
    onRemove: (Int) -> Unit,
    onEdit: (Int) -> Unit,
    onClick: (Int) -> Unit
) {

    val remove = SwipeAction(
        icon = rememberVectorPainter(Icons.TwoTone.Delete),
        background = MaterialTheme.colors.Green,
        onSwipe = { employee.id?.let { onEdit.invoke(it) } }
    )

    val edit = SwipeAction(
        icon = rememberVectorPainter(Icons.TwoTone.Edit),
        background = MaterialTheme.colors.Yellow,
        isUndo = true,
        onSwipe = { employee.id?.let { onRemove.invoke(it) } },
    )

    SwipeableActionsBox(
        startActions = listOf(edit),
        endActions = listOf(remove)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { employee.id?.let { onClick.invoke(it) } },
            verticalAlignment = Alignment.CenterVertically
        ) {
            employee.image?.let {
                CircularImage(imageUrl = it, widthFraction = 0.35f)
            }
            EmployeeContent(
                employee = employee
            )
        }
    }
}

@Composable
fun EmployeeContent(
    employee: Employee,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = MaterialTheme.spacing.small,
        )
    ) {
        Text(
            text = employee.name.orEmpty(),
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge
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