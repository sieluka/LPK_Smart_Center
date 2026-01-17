package com.example.lpksmartcenter.notifications

import android.app.NotificationManager

data class NotificationConfig(
    val channelId: String,
    val channelName: String,
    val channelDescription: String,
    val importance: Int = NotificationManager.IMPORTANCE_HIGH,
    val enableVibration: Boolean = true,
    val showBadge: Boolean = true
)