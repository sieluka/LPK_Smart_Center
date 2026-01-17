package com.example.lpksmartcenter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lpksmartcenter.screens.DevicesScreen
import com.example.lpksmartcenter.screens.FanScreen
import com.example.lpksmartcenter.screens.LightScreen
import com.example.lpksmartcenter.screens.LoginScreen
import com.example.lpksmartcenter.screens.ProfileScreen
import com.example.lpksmartcenter.screens.SensorsScreen
import com.google.firebase.auth.FirebaseAuth

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Main : Screen("main")
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
    val route: String
) {
    SENSORS("Sensors", Icons.Default.Star, "sensors"),
    DEVICES("Devices", Icons.Default.Build, "devices"),
    PROFILE("Profile", Icons.Default.Person, "profile"),
}

object DeviceScreens {
    const val FAN = "device/fan"
    const val LIGHT = "device/light"
}

@Composable
fun LPKSmartCenterNavigation(auth: FirebaseAuth) {
    val navController = rememberNavController()
    val startDestination = if (auth.currentUser != null) {
        Screen.Main.route
    } else {
        Screen.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            LPKSmartCenterApp(
                onNavigateToLogin = {
                    auth.signOut()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun LPKSmartCenterApp(
    onNavigateToLogin: () -> Unit = {}
) {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val currentDestination = when {
        currentRoute?.startsWith("device/") == true -> null
        else -> AppDestinations.entries.find { it.route == currentRoute }
            ?: AppDestinations.SENSORS
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = { Icon(destination.icon, contentDescription = destination.label) },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = {
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppDestinations.SENSORS.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(AppDestinations.SENSORS.route) {
                    SensorsScreen()
                }

                composable(AppDestinations.DEVICES.route) {
                    DevicesScreen(
                        onNavigateToFan = {
                            navController.navigate(DeviceScreens.FAN)
                        },
                        onNavigateToLight = {
                            navController.navigate(DeviceScreens.LIGHT)
                        }
                    )
                }

                composable(AppDestinations.PROFILE.route) {
                    ProfileScreen(onLogout = onNavigateToLogin)
                }

                composable(DeviceScreens.FAN) {
                    FanScreen()
                }

                composable(DeviceScreens.LIGHT) {
                    LightScreen()
                }
            }
        }
    }
}
