package com.makentoshe.mathworks

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.preference.PreferenceManager
import java.util.*


class AutoStartService() : Service() {
    var counter = 0
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startTimer()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        val broadcastIntent = Intent(this, RestartBroadcastReceiver::class.java)
        sendBroadcast(broadcastIntent)
//        stoptimertask()
    }

    fun startTimer() {
        timer = Timer()

        initialiseTimerTask()

        timer?.schedule(timerTask, 1000, 5000) //
    }

    fun initialiseTimerTask() {
        timerTask = object : TimerTask() {
            var lives=PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("lives",3)
            override fun run() {
                if (lives < 3)
                    {lives += 1
                    PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("lives",3).apply()}
                else
                    stoptimertask()
            }
        }
    }

    fun stoptimertask() {
        if (timer != null) {
            timer?.cancel()
            timer = null
        }
    }

    companion object {
        private const val TAG = "AutoService"
    }

    init {
    }
}

class RestartBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?){
        context.startService(Intent(context, AutoStartService::class.java))
    }
}