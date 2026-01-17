package com.example.lpksmartcenter.notifications

import androidx.core.app.NotificationCompat

data class NotificationContent(
    val title: String,
    val text: String,
    val priority: Int = NotificationCompat.PRIORITY_HIGH,
    val category: String = NotificationCompat.CATEGORY_STATUS,
    val autoCancel: Boolean = true
)
