package uz.hashteam.dummycompose.ui

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.hashteam.dummycompose.ui.navigation.EmployeesScreen
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
                val activity = (LocalContext.current as? Activity)

                EmployeesScreen(
                    systemBackButtonClicked = { activity?.finish() },
                    onClick = {
                        scope.launch {

                        }
                    },
                    onEdit = {
                        scope.launch {

                        }
                    },
                    onRemove = {
                        scope.launch {

                        }
                    }
                )
            }
        }
    }
}