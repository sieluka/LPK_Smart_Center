package com.example.lpksmartcenter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.R
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

/*@Composable
fun SensorsScreen(
    modifier: Modifier = Modifier,
    viewModel: SensorsViewModel = viewModel()
) {

    val sensorData by viewModel.sensorData.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        //.padding(top = 110.dp),
        contentPadding = PaddingValues(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        item {
            SenorsCard(
                text = "Temperatura:",
                text1 = sensorData.temperature,
                icon = painterResource(R.drawable.outline_thermometer_24),
            )
        }
        item {
            SenorsCard(
                text = "Wilgotność",
                text1 = sensorData.humidity,
                icon = painterResource(R.drawable.outline_humidity_percentage_24),
            )
        }
        item {
            SenorsCard(
                text = "Trzecia karta",
                text1 = "22°C",
                icon = painterResource(R.drawable.outline_thermometer_24)
            )
        }

        item {
            SenorsCard(
                text = "Trzecia karta",
                text1 = "22°C",
                icon = painterResource(R.drawable.outline_thermometer_24)
            )
        }
        item {
            SenorsCard(
                text = "Trzecia karta",
                text1 = "22°C",
                icon = painterResource(R.drawable.outline_thermometer_24)
            )
        }
        item {
            SenorsCard(
                text = "Trzecia karta",
                text1 = "22°C",
                icon = painterResource(R.drawable.outline_thermometer_24)
            )
        }
        item {
            SenorsCard(
                text = "Trzecia karta",
                text1 = "22°C",
                icon = painterResource(R.drawable.outline_thermometer_24)
            )
        }
        item {
            SenorsCard(
                text = "Trzecia karta",
                text1 = "22°C",
                icon = painterResource(R.drawable.outline_thermometer_24)
            )
        }
        item {
            SenorsCard(
                text = "Trzecia karta",
                text1 = "22°C",
                icon = painterResource(R.drawable.outline_thermometer_24)
            )
        }

    }
}*/

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
                "DHT11-temperatura" -> "$value°C"
                "DHT11-wilgotnosc" -> "$value%"
                "Deszcz" -> when (value) {
                    "1" -> "Pada"
                    "0" -> "Nie pada"
                    else -> value
                }
                "Ruch" -> when (value) {
                    "1" -> "Wykryto ruch"
                    "0" -> "Nie wykryto ruchu"
                    else -> value
                }
                "GAS" -> when (value) {
                    "0" -> "Wykryto GAZ"
                    "1" -> "Nie wykryto GAZU"
                    else -> value
                }
                else -> when (value) {
                    "true" -> "Okno jest otwarte"
                    "false" -> "Okno jest zamknięte"
                    else -> value
                }
            }

            val displayIcon = when (key) {
                "DHT11-temperatura" -> painterResource(R.drawable.outline_thermometer_24)
                "DHT11-wilgotnosc" -> painterResource(R.drawable.outline_humidity_percentage_24)
                "GAS" -> painterResource(R.drawable.outline_gas_meter_24)
                "Deszcz" -> painterResource(R.drawable.outline_rainy_24)
                "Kontaktrony" -> painterResource(R.drawable.outline_sensor_window_24)
                "Ruch" -> painterResource(R.drawable.outline_directions_walk_24)
                else -> painterResource(R.drawable.outline_thermometer_24)
            }


            SenorsCard(
                text = key,
                text1 = displayValue,
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