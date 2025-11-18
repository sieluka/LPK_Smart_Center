package com.example.lpksmartcenter.ui.theme


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lpksmartcenter.R
import com.example.lpksmartcenter.data.SensorsDataBase
import com.example.lpksmartcenter.viewmodel.SensorsViewModel


@Composable
fun SenorsCard(
    text: String,
    text1: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            //containerColor = MaterialTheme.colorScheme.primaryContainer,
            //contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = modifier
            .size(width = 350.dp, height = 80.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp),
            )
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = text1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun DeviceCard(
    text: String,
    icon: Painter,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    //val sensorsDB = remember { SensorsDataBase() }
    //var checked by remember { mutableStateOf(false) }
    val statusText = if (checked) stringResource(R.string.on) else stringResource(R.string.off)

    // Uniwersalny odczyt dla dowolnego urządzenia
    /*LaunchedEffect(deviceKey) {
        if (deviceKey.isNotEmpty()) {
            sensorsDB.readDeviceState(deviceKey) { state ->
                checked = state
            }
        }
    }*/


    ElevatedCard(
        colors = CardDefaults.cardColors(
            //containerColor = MaterialTheme.colorScheme.primaryContainer,
            //contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = modifier
            .size(width = 350.dp, height = 80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp),
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = statusText,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            /*if (deviceKey.isNotEmpty()) {
                SwitchMinimalExample(
                    checked = checked,
                    onCheckedChange = { newState ->
                        checked = newState
                        sensorsDB.writeDeviceState(deviceKey, newState)
                    }
                )
            }*/
            SwitchMinimalExample(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PlaylistScreenPreview() {
    LPKSmartCenterTheme {
        SenorsCard(
            "Druga Karta", "Opis drugiej karty",
            painterResource(R.drawable.outline_humidity_percentage_24 )
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun PlaylistScreenPreview1() {
    LPKSmartCenterTheme {
        DeviceCard(
            text = "Druga Karta",
            icon = painterResource(R.drawable.outline_humidity_percentage_24)
        )
    }
}*/