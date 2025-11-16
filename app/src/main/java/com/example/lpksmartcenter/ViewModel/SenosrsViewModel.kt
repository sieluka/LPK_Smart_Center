package com.example.lpksmartcenter.ViewModel

import androidx.lifecycle.ViewModel
import com.example.lpksmartcenter.data.SensorData
import com.example.lpksmartcenter.data.SensorsDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorsViewModel : ViewModel() {
    private val sensorsDataBase = SensorsDataBase()

    private val _sensorData = MutableStateFlow(SensorData())
    val sensorData: StateFlow<SensorData> = _sensorData.asStateFlow()

    init {
        sensorsDataBase.readData { data ->
            _sensorData.value = data
        }
    }
}