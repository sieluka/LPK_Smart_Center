package com.example.lpksmartcenter

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.lpksmartcenter.notifications.NotificationHelper
import com.example.lpksmartcenter.ui.theme.LPKSmartCenterTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            createNotificationChannels()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkNotificationPermission()
        subscribeToNotifications()
        logFcmToken()

        setContent {
            LPKSmartCenterTheme {
                LPKSmartCenterNavigation(auth = auth)
            }
        }
    }

    private fun createNotificationChannels() {
        val notificationHelper = NotificationHelper(this)
        NotificationHelper.getAllChannelConfigs().forEach { config ->
            notificationHelper.createChannel(config)
        }
        Log.d("MainActivity", "Notification channels created")
    }

    private fun subscribeToNotifications() {
        FirebaseMessaging.getInstance().subscribeToTopic("all_devices")
            .addOnCompleteListener { task ->
                var msg = "Subscribed to notifications"
                if (!task.isSuccessful) {
                    msg = "Subscription failed"
                }
                Log.d("MainActivity", msg)
            }
    }

    private fun logFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            Log.d(TAG, "FCM Token: ${task.result}")
        }
    }
    private fun checkNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                createNotificationChannels()
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}


