package com.example.lpksmartcenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lpksmartcenter.ui.theme.LPKSmartCenterTheme

@Composable
fun NotificationsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Album Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    LPKSmartCenterTheme() {
        DevicesScreen()
    }
}