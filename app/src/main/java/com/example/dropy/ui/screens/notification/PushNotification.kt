package com.example.dropy.ui.screens.notification

import com.example.dropy.ui.screens.notification.NotificationData

data class PushNotification(
    val data: NotificationData,
    val to: String
)