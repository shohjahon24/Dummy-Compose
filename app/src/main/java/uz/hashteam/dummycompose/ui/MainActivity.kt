package uz.hashteam.dummycompose.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.hashteam.dummycompose.ui.navigation.DetailScreen
import uz.hashteam.dummycompose.ui.navigation.EmployeesScreen
import uz.hashteam.dummycompose.ui.presentation.detailscreen.DetailsScreen
import uz.hashteam.dummycompose.ui.presentation.employeesscreen.EmployeesScreen
import uz.hashteam.dummycompose.ui.theme.DummyTheme
import uz.hashteam.dummycompose.ui.theme.spacing

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DummyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    tonalElevation = MaterialTheme.spacing.small
                ) {
                    DummyComposeApp()
                }
            }
        }
    }

}

@Composable
fun DummyComposeApp(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val activity = (LocalContext.current as? Activity)
    Scaffold(modifier = modifier) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = EmployeesScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                EmployeesScreen.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                }
            ) {
                EmployeesScreen(
                    systemBackButtonClicked = { activity?.finish() },
                    navController = navController
                )
            }

            composable(
                DetailScreen.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                }
            ) {
                it.arguments?.getString("userId")?.let { it1 ->
                    DetailsScreen(
                        systemBackButtonClicked = { activity?.finish() }, userId = it1.toInt(),
                    )
                }
            }
        }
    }
}