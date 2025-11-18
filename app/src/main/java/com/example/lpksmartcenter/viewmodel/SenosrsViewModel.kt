package com.example.lpksmartcenter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lpksmartcenter.data.SensorsDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/*class SensorsViewModel : ViewModel() {
    private val sensorsDataBase = SensorsDataBase()

    private val _sensorData = MutableStateFlow(SensorData())
    val sensorData: StateFlow<SensorData> = _sensorData.asStateFlow()

    init {
        sensorsDataBase.readData { data ->
            _sensorData.value = data
        }
    }
}*/

class SensorsViewModel(
    private val db: SensorsDataBase = SensorsDataBase()
) : ViewModel() {

    private val _sensorsData = MutableStateFlow<Map<String, String>>(emptyMap())
    val sensorsData: StateFlow<Map<String, String>> = _sensorsData.asStateFlow()

    init {
        db.readSensorsData { data ->
            _sensorsData.value = data
        }
    }
}