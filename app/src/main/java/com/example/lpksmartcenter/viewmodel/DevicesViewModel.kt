package com.example.lpksmartcenter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lpksmartcenter.data.DevicesDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DevicesViewModel : ViewModel() {
    private val devicesDataBase = DevicesDataBase()

    private val _devicesData = MutableStateFlow<Map<String, Any>>(emptyMap())
    val devicesData: StateFlow<Map<String, Any>> = _devicesData.asStateFlow()

    init {
        devicesDataBase.readDevicesData { data ->
            _devicesData.value = data
        }
    }

    fun updateDeviceState(key: String, newState: Boolean) {
        devicesDataBase.writeDeviceState(key, newState)
    }

    fun updateLampBrightness(key: String, brightness: Int) {
        devicesDataBase.writeLampBrightness(key, brightness)
    }

}