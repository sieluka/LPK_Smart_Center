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
                "Temperatura" -> "$value°C"
                "Wilgotnosc" -> "$value%"
                "Deszcz" -> when (value) {
                    1, 1L, 1.0 -> "Pada"
                    0, 0L, 0.0 -> "Nie pada"
                    else -> value
                }
                "Ruch" -> when (value) {
                    1, 1L, 1.0 -> "Wykryto ruch"
                    0, 0L, 0.0 -> "Nie wykryto ruchu"
                    else -> value
                }
                "Gaz-Dym" -> when (value) {
                    0, 0L, 0.0 -> "WYKRYTO GAZ"
                    1, 1L, 1.0 -> "Nie wykryto gazu"
                    else -> value
                }
                "Kontaktrony" -> when (value) {
                    true -> "Okno jest otwarte"
                    false -> "Okno jest zamknięte"
                    else -> value
                }
                "Wiatrak" -> when (value) {
                    true -> "Wiatrak jest włączony"
                    false -> "Wiatrak jest wyłączony"
                    else -> value

                }
                else -> value
            }

            val displayIcon = when (key) {
                "Temperatura" -> painterResource(R.drawable.outline_thermometer_24)
                "Wilgotnosc" -> painterResource(R.drawable.outline_humidity_percentage_24)
                "GAS" -> painterResource(R.drawable.outline_gas_meter_24)
                "Deszcz" -> painterResource(R.drawable.outline_rainy_24)
                "Kontaktrony" -> painterResource(R.drawable.outline_sensor_window_24)
                "Ruch" -> painterResource(R.drawable.outline_directions_walk_24)
                "Wiatrak" -> when (value) {
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