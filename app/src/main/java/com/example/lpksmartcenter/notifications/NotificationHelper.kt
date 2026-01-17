package com.example.lpksmartcenter.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.lpksmartcenter.MainActivity
import com.example.lpksmartcenter.R

class NotificationHelper(private val context: Context) {

    private val channels = mutableMapOf<String, NotificationConfig>()
    private var notificationIdCounter = 0

    companion object {
        // ID kanałów
        const val RAIN_CHANNEL_ID = "rain_sensor_channel"
        const val GAS_CHANNEL_ID = "gas_sensor_channel"
        const val WINDOW_CHANNEL_ID = "window_sensor_channel"
        const val MOTION_CHANNEL_ID = "motion_sensor_channel"
        const val FAN_CHANNEL_ID = "fan_channel"
        const val GATE_CHANNEL_ID = "gate_channel"
        const val DEFAULT_CHANNEL_ID = "default_channel"


        fun getAllChannelConfigs(): List<NotificationConfig> {
            return listOf(
                NotificationConfig(
                    channelId = RAIN_CHANNEL_ID,
                    channelName = "Czujnik deszczu",
                    channelDescription = "Powiadomienia o wykryciu deszczu",
                    importance = NotificationManager.IMPORTANCE_HIGH
                ),
                NotificationConfig(
                    channelId = GAS_CHANNEL_ID,
                    channelName = "Czujnik gazu",
                    channelDescription = "Powiadomienia o wykryciu gazu",
                    importance = NotificationManager.IMPORTANCE_HIGH
                ),
                NotificationConfig(
                    channelId = WINDOW_CHANNEL_ID,
                    channelName = "Czujnik okien",
                    channelDescription = "Powiadomienia o otwarciu okien",
                    importance = NotificationManager.IMPORTANCE_HIGH
                ),
                NotificationConfig(
                    channelId = MOTION_CHANNEL_ID,
                    channelName = "Czujnik ruchu",
                    channelDescription = "Powiadomienia o wykryciu ruchu",
                    importance = NotificationManager.IMPORTANCE_HIGH
                ),
                NotificationConfig(
                    channelId = FAN_CHANNEL_ID,
                    channelName = "Wiatrak",
                    channelDescription = "Powiadomienia o stanie wiatraka",
                    importance = NotificationManager.IMPORTANCE_HIGH
                ),
                NotificationConfig(
                    channelId = GATE_CHANNEL_ID,
                    channelName = "Brama",
                    channelDescription = "Powiadomienia o stanie bramy",
                    importance = NotificationManager.IMPORTANCE_HIGH
                ),
                NotificationConfig(
                    channelId = DEFAULT_CHANNEL_ID,
                    channelName = "Ogólne",
                    channelDescription = "Pozostałe powiadomienia",
                    importance = NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
    }

    fun createChannel(config: NotificationConfig) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                config.channelId,
                config.channelName,
                config.importance
            ).apply {
                description = config.channelDescription
                enableVibration(config.enableVibration)
                setShowBadge(config.showBadge)
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            channels[config.channelId] = config
        }
    }

    fun sendNotification(
        channelId: String,
        content: NotificationContent,
        notificationId: Int = notificationIdCounter++
    ) {
        if (!hasNotificationPermission()) {
            return
        }

        // Create an explicit intent for an Activity in your app.
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_1_foreground)
            .setContentTitle(content.title)
            .setContentText(content.text)
            .setPriority(content.priority)
            .setCategory(content.category)
            .setAutoCancel(content.autoCancel)
            // Set the intent that fires when the user taps the notification.
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                // ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array&lt;out String&gt;,
                //                                        grantResults: IntArray)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return@with
            }
            // notificationId is a unique int for each notification that you must define.
            notify(notificationId, builder.build())
        }
    }

    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }
}
