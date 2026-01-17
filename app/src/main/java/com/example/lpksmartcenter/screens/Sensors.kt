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
import com.example.lpksmartcenter.ui.theme.LPKSmartCenterTheme
import com.example.lpksmartcenter.R
import com.example.lpksmartcenter.viewmodel.SensorsViewModel
import com.example.lpksmartcenter.ui.theme.SenorsCard

@Composable
fun SensorsScreen(
    modifier: Modifier = Modifier,
    viewModel: SensorsViewModel = viewModel()
)
{
    val sensorsData by viewModel.sensorsData.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(sensorsData.entries.toList()) { (key, value) ->

            val displayValue = when (key){
                stringResource(R.string.czujnik_temperatury) -> "$value°C"
                stringResource(R.string.czujnik_wilgotnosci) -> "$value%"
                stringResource(R.string.czujnik_deszczu) -> when (value) {
                    false -> stringResource(R.string.pada_deszcz)
                    true -> stringResource(R.string.nie_pada_descz)
                    else -> value
                }
                stringResource(R.string.czujnik_ruchu) -> when (value) {
                    true -> stringResource(R.string.wykryto_ruch)
                    false -> stringResource(R.string.nie_wykryto_ruchu)
                    else -> value
                }
                stringResource(R.string.czujnik_gazu_dymu) -> when (value) {
                    false -> stringResource(R.string.wykryto_gaz)
                    true -> stringResource(R.string.nie_wykryto_gazu)
                    else -> value
                }
                stringResource(R.string.czujnik_otwarcia_okna) -> when (value) {
                    true -> stringResource(R.string.okno_jest_otwarte)
                    false -> stringResource(R.string.okno_jest_zamkniete)
                    else -> value
                }
                stringResource(R.string.czujnik_wiatraka) -> when (value) {
                    true -> stringResource(R.string.wiatrak_jest_wlaczony)
                    false -> stringResource(R.string.wiatrak_jest_wylaczony)
                    else -> value

                }
                else -> value
            }

            val displayIcon = when (key) {
                stringResource(R.string.czujnik_temperatury) -> painterResource(R.drawable.outline_device_thermostat_24)
                stringResource(R.string.czujnik_wilgotnosci) -> painterResource(R.drawable.outline_humidity_percentage_24)
                stringResource(R.string.czujnik_gazu_dymu) -> painterResource(R.drawable.outline_gas_meter_24)
                stringResource(R.string.czujnik_deszczu) -> painterResource(R.drawable.outline_rainy_24)
                stringResource(R.string.czujnik_otwarcia_okna) -> painterResource(R.drawable.outline_sensor_window_24)
                stringResource(R.string.czujnik_ruchu) -> painterResource(R.drawable.outline_directions_walk_24)
                stringResource(R.string.czujnik_wiatraka) -> when (value) {
                    true -> painterResource(R.drawable.outline_mode_fan_24)
                    false -> painterResource(R.drawable.outline_mode_fan_off_24)
                    else ->  painterResource(R.drawable.outline_mode_fan_24)
                }
                else -> painterResource(R.drawable.outline_thermometer_24)
            }

            SenorsCard(
                text = key,
                text1 = displayValue.toString(),
                icon = displayIcon,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SongsScreenPreview() {
    LPKSmartCenterTheme {
        SensorsScreen()
    }
}