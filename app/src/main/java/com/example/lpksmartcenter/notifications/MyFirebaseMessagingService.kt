package com.example.lpksmartcenter.notifications

import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        // Sprawdź czy wiadomość zawiera dane
        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            handleDataMessage(remoteMessage.data)
        }

        // Sprawdź czy wiadomość zawiera powiadomienie
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.title ?: "", it.body ?: "")
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        // Wyślij token do serwera, jeśli potrzebny
        sendTokenToServer(token)
    }

    private fun handleDataMessage(data: Map<String, String>) {
        val title = data["title"] ?: ""
        val body = data["body"] ?: ""

        Log.d(TAG, "Handling data message: title=$title, body=$body")
        sendNotification(title, body)
    }

    private fun sendNotification(title: String, body: String) {
        val notificationHelper = NotificationHelper(this)

        val channelId = when {
            title.contains("Deszcz") -> NotificationHelper.RAIN_CHANNEL_ID
            title.contains("Gaz") -> NotificationHelper.GAS_CHANNEL_ID
            title.contains("okna") -> NotificationHelper.WINDOW_CHANNEL_ID
            title.contains("ruchu") -> NotificationHelper.MOTION_CHANNEL_ID
            title.contains("Wiatrak") -> NotificationHelper.FAN_CHANNEL_ID
            title.contains("Brama") -> NotificationHelper.GATE_CHANNEL_ID
            else -> "default_channel"
        }

        val content = NotificationContent(
            title = title,
            text = body,
            priority = NotificationCompat.PRIORITY_HIGH
        )

        Log.d(TAG, "Sending notification to channel: $channelId")
        notificationHelper.sendNotification(channelId, content)
    }

    private fun sendTokenToServer(token: String) {
        Log.d(TAG, "Token should be sent to server: $token")
    }

    companion object {
        private const val TAG = "FCMService"
    }
}


