package com.example.lpksmartcenter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lpksmartcenter.R
import com.example.lpksmartcenter.ui.theme.BasicSlider
import com.example.lpksmartcenter.viewmodel.DevicesViewModel


@Composable
fun LightScreen(
    modifier: Modifier = Modifier,
    viewModel: DevicesViewModel = viewModel()
){
    val device = stringResource(R.string.lampa)

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        BasicSlider(
            viewModel = viewModel,
            key = device
        )
    }
}

@Preview
@Composable
fun LightScreenPreview() {
    LightScreen()
}