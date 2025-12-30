package com.example.lpksmartcenter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lpksmartcenter.data.SensorsDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class SensorsViewModel : ViewModel() {
    private val sensorsDataBase = SensorsDataBase()
    private val _sensorsData = MutableStateFlow<Map<String, Any>>(emptyMap())
    val sensorsData: StateFlow<Map<String, Any>> = _sensorsData.asStateFlow()

    init {
        sensorsDataBase.readSensorsData { data ->
            _sensorsData.value = data
        }
    }
}

