package com.makentoshe.mathworks

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.layout_hidden.*


class MenuHidden : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_hidden)
        var permitReset=false
        val notificationName=getString(R.string.app_name)
        val notificationText=getString(R.string.result_good)
        val prefs = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        var lives= prefs.getInt("lives", 3)
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
        hiddenStopServiceButton.setOnClickListener {
            Intent(applicationContext, EndlessService::class.java).also {
                it.action = Actions.STOP.name
                stopService(it)
            }
        }
        hiddenToMainButton.setOnClickListener {
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
        hiddenSendNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(15, builder.build())
            }
        }
        uselessButton1.setOnClickListener {
            if(lives>0) lives--
            Log.d("MenuHidden","Lives decreased and is now $lives")
            prefs.edit().putInt("lives",lives).apply()
            actionOnService(applicationContext,Actions.START)
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
            if(lives<3)lives++
            Log.d("MenuHidden","Lives increased and is now $lives")
            prefs.edit().putInt("lives",lives).apply()
            actionOnService(applicationContext,Actions.START)
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
        hiddenUnlockResetButton.setOnClickListener {
            if(permitReset) {permitReset=false; resetButton.setBackgroundColor(Color.GRAY)}
            else {permitReset=true; resetButton.setBackgroundColor(Color.RED)}
        }
        resetButton.setOnClickListener {
            if(permitReset){
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().clear().apply()
                finish()
                startActivity(intent)}
        }
        switch1.isChecked=PreferenceManager.getDefaultSharedPreferences(applicationContext).getBoolean("allowWandering",false)
        switch1.setOnCheckedChangeListener { _, isChecked ->  PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putBoolean("allowWandering",isChecked).apply()}
    }
    private fun actionOnService(context: Context,action: Actions) {
        if (getServiceState(this) == ServiceState.STOPPED && action == Actions.STOP) return
        Intent(this, EndlessService::class.java).also {
            it.action = action.name
            startService(it)
        }
    }
}