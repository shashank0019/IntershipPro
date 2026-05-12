package com.karunadavanya.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtils {
    fun formatTimestamp(millis: Long): String {
        val formatter = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
        return formatter.format(Date(millis))
    }

    fun expiredThresholdHours(hours: Long = 6): Long {
        return System.currentTimeMillis() - hours * 60 * 60 * 1000
    }
}
