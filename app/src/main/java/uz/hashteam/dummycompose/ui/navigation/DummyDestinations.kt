package uz.hashteam.dummycompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Available destinations in the app
 */
interface Destination {
    val route: String
    val label: String
    val icon: ImageVector
}

object EmployeesScreen : Destination {
    override val route = "employees_list"
    override val label = "Employees"
    override val icon = Icons.Default.Menu
}

object DetailScreen : Destination {
    override val route = "detail/{userId}"
    override val label = "Detail"
    override val icon = Icons.Default.Notifications
}
