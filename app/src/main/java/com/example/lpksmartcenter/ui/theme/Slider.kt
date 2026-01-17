package com.example.lpksmartcenter.ui.theme


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lpksmartcenter.R
import com.example.lpksmartcenter.viewmodel.DevicesViewModel

@Composable
fun LightSlider(
    viewModel: DevicesViewModel = viewModel(),
    key: String
)
{
    val devicesData by viewModel.devicesData.collectAsState()
    var sliderPosition by rememberSaveable {
        mutableFloatStateOf(
            (devicesData[key] as? Long)?.toFloat() ?: 0f
        )
    }

    LaunchedEffect(devicesData[key]) {
        val newValue = (devicesData[key] as? Long)?.toFloat()
        if (newValue != null) {
            sliderPosition = newValue
        }
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.jasnosc_swiatla) + sliderPosition.toInt().toString(),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Slider(
            value = sliderPosition,
            onValueChange = { newValue ->
                sliderPosition = newValue
            },
            onValueChangeFinished = {
                viewModel.updateLampBrightness(key, sliderPosition.toInt())
            },
            valueRange = 0f..255f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewM3Slider() {
    LightSlider(key = "Lampa")
}

