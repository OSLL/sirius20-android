package com.makentoshe.mathworks

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.preference.PreferenceManager


class TimerReceiverSyncInterval : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onReceive(context: Context, intent: Intent?) {
        scheduleAlarms(context)
        context.startService(Intent(context, NotificationServiceSyncInterval::class.java))
    }

    companion object {
        @SuppressLint("ShortAlarm")
        @RequiresApi(Build.VERSION_CODES.N)
        fun scheduleAlarms(paramContext: Context) {
            val calendar: Calendar = Calendar.getInstance()
            val localAlarmManager = paramContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val localPendingIntent = PendingIntent.getService(paramContext, 0,
                    Intent(paramContext, NotificationServiceSyncInterval::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
            localAlarmManager.setRepeating(AlarmManager.RTC, calendar.timeInMillis,
                    (1200000).toLong(), localPendingIntent)
        }
    }
}
class NotificationServiceSyncInterval : IntentService {
    constructor() : super("Tracker") {}
    constructor(paramString: String?) : super(paramString) {}

    override fun onHandleIntent(intent: Intent?) {
        var lives= PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("lives", 3)
        if (lives<3) {
            lives += 1; PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("lives", lives).apply()
            val notificationName = getString(R.string.app_name)
            val notificationText = "$lives"
            val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(this, "OVER9000")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(notificationName)
                    .setContentText(notificationText)
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                        "OVER9000", "My channel",
                        NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = "My channel deserves no description"
                channel.enableLights(true)
                channel.lightColor = Color.GREEN
                channel.enableVibration(true)
                notificationManager.createNotificationChannel(channel)
            }
            with(NotificationManagerCompat.from(this)) {
                notify(15, builder.build())
            }
        }
    }
}