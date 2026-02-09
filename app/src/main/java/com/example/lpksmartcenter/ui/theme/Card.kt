package com.example.lpksmartcenter.ui.theme


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lpksmartcenter.R

@Composable
fun SenorsCard(
    text: String,
    text1: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)

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
    ElevatedCard(
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
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
                        .fillMaxWidth(),
                )
            }
            SwitchMinimalExample(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
fun FanCard(
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
    ) {

    ElevatedCard(
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() }
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
                    .padding(start = 16.dp)
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
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun LightCard(
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    ElevatedCard(
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() }
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
                    .padding(start = 16.dp)
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
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SensorScreenPreview() {
    LPKSmartCenterTheme {
        SenorsCard(
            "Druga Karta", "Opis drugiej karty",
            painterResource(R.drawable.outline_humidity_percentage_24 )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeviceScreenPreview() {
    LPKSmartCenterTheme {
        DeviceCard(
            text = "Druga Karta",
            icon = painterResource(R.drawable.outline_humidity_percentage_24),
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FanScreenPreview(){
    LPKSmartCenterTheme {
        FanCard(
            text = "ddd",
            icon = painterResource(R.drawable.outline_mode_fan_24)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LightScreenPreview(){
    LPKSmartCenterTheme {
        LightCard(
            text = "xxx",
            icon = painterResource(R.drawable.outline_light_24)
        )
    }
}