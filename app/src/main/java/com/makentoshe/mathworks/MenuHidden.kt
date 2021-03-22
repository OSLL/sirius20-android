package com.makentoshe.mathworks

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.layout_hidden.*
import java.util.*
import kotlin.concurrent.schedule


class MenuHidden : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_hidden)
        val notificationName=getString(R.string.app_name)
        val notificationText=getString(R.string.result_good)
        setupToMainButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, "OVER9000")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(notificationName)
                .setContentText(notificationText)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this,0,Intent(this,MainActivity::class.java),PendingIntent.FLAG_UPDATE_CURRENT))
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
        setupSendNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(15, builder.build())
            }
        }
        hiddenCheatLifeButton.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("lives",3).apply()
        }
    }
}