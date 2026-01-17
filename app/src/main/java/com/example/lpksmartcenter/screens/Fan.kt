package com.example.lpksmartcenter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lpksmartcenter.R
import com.example.lpksmartcenter.ui.theme.DeviceCard
import com.example.lpksmartcenter.viewmodel.DevicesViewModel
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun FanScreen(
    modifier: Modifier = Modifier,
    viewModel: DevicesViewModel = viewModel()
){
    val devicesData by viewModel.devicesData.collectAsState()
    val filteredDevices = devicesData.filter { (key, _) ->
        key != stringResource(R.string.brama) && key != stringResource(R.string.lampa)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(filteredDevices.entries.toList()) { (key, isOn) ->

            val displayIcon = when (key) {
                stringResource(R.string.tryb_auto_wiatraka) -> painterResource(R.drawable.outline_rotate_auto_24)
                else -> painterResource(R.drawable.outline_mode_fan_24)
            }

            DeviceCard(
                text = key,
                icon = displayIcon,
                checked = isOn as? Boolean ?: false,
                onCheckedChange = { newState ->
                    viewModel.updateDeviceState(key, newState)
                }
            )
        }
    }
}

@Preview
@Composable
fun FanScreenPreview() {
    FanScreen()
}