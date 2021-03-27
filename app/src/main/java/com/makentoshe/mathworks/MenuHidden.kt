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


class MenuHidden : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_hidden)
        val notificationName=getString(R.string.app_name)
        val notificationText=getString(R.string.result_good)
        var lives= PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("lives",3)
        when(lives){
            3->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                heart2.setImageResource(R.drawable.ic_favorite_24px)
                heart3.setImageResource(R.drawable.ic_favorite_24px)
            }
            2->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                heart2.setImageResource(R.drawable.ic_favorite_24px)
                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
            }
            1->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
            }
            0->{heart1.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)}
        }
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
        uselessButton1.setOnClickListener {
            if (lives>0) lives--
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("lives",lives).apply()
            when(lives){
                3->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_24px)
                    heart3.setImageResource(R.drawable.ic_favorite_24px)
                }
                2->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_24px)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                }
                1->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                }
                0->{heart1.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)}
            }
        }
        uselessButton2.setOnClickListener {
            if (lives<3) lives++
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("lives",lives).apply()
            when(lives){
                3->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_24px)
                    heart3.setImageResource(R.drawable.ic_favorite_24px)
                }
                2->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_24px)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                }
                1->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                }
                0->{heart1.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)}
            }
        }
    }
}