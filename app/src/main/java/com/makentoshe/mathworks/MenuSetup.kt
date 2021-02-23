package com.makentoshe.mathworks

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.layout_menu_setup.*


class MenuSetup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_setup)
        var notificationName=getString(R.string.app_name)
        var notificationText=getString(R.string.result_good)
        setupHiddenNotificationName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { notificationName=setupHiddenNotificationName.text.toString().trim() }
        })
        setupHiddenNotificationText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { notificationText=setupHiddenNotificationText.text.toString().trim() }
        })
        setupToMainButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "OVER9000", "My channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "My channel description"
            channel.enableLights(true)
            channel.lightColor = Color.GREEN
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }
        setupSendNotificationButton.setOnClickListener {
            val builder = NotificationCompat.Builder(this, "OVER9000")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(notificationName)
                    .setContentText(notificationText)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(15, builder.build())
            }
        }
    }
}