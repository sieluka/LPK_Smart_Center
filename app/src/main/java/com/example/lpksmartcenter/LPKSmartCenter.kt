package com.example.lpksmartcenter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.lpksmartcenter.screens.DevicesScreen
import com.example.lpksmartcenter.screens.NotificationsScreen
import com.example.lpksmartcenter.screens.SensorsScreen
import com.example.lpksmartcenter.ui.theme.LPKSmartCenterTheme

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    SENSORS("Sensors", Icons.Default.Star),
    DEVICES("Devices", Icons.Default.Build),
    NOTIFICATIONS("Notifications", Icons.Default.Notifications),
}

@PreviewScreenSizes
@Composable
fun LPKSmartCenterApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SENSORS) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        )
        { innerPadding ->
            when (currentDestination) {
                AppDestinations.SENSORS -> SensorsScreen(modifier = Modifier.padding(innerPadding))
                AppDestinations.DEVICES -> DevicesScreen(modifier = Modifier.padding(innerPadding))
                AppDestinations.NOTIFICATIONS -> NotificationsScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}



