package com.example.lpksmartcenter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
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
import com.example.lpksmartcenter.ui.theme.LPKSmartCenterTheme
import com.example.lpksmartcenter.viewmodel.SensorsViewModel


@Composable
fun DevicesScreen(
    modifier: Modifier = Modifier,

) {


    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        item {
            DeviceCard(
                text = stringResource(R.string.wiatrak),
                icon = painterResource(R.drawable.outline_humidity_percentage_24),
                //deviceKey = "Fan"
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlbumScreenPreview() {
    LPKSmartCenterTheme() {
        DevicesScreen()
    }
}