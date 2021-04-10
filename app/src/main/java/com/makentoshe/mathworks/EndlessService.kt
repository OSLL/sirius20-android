package com.makentoshe.mathworks

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.*


class EndlessService : Service() {

    private var wakeLock: PowerManager.WakeLock? = null
    private var isServiceStarted = false

    override fun onBind(intent: Intent): IBinder? {
        Log.d("ES","Some component want to bind with the service")
        // We don't provide binding, so return null
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ES","onStartCommand executed with startId: $startId")
        if (intent != null) {
            val action = intent.action
            Log.d("ES","using an intent with action $action")
            when (action) {
                Actions.START.name -> startService()
                Actions.STOP.name -> stopService()
                else -> Log.d("ES","This should never happen. No action in the received intent")
            }
        } else {
            Log.d("ES",
                    "with a null intent. It has been probably restarted by the system."
            )
        }
        // by returning this we make sure the service is restarted if the system kills the service
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("ES","The service has been created".toUpperCase())
//        val notification = createNotification()
//        startForeground(1, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ES","The service has been destroyed".toUpperCase())
    }

    override fun onTaskRemoved(rootIntent: Intent) {
        val restartServiceIntent = Intent(applicationContext, EndlessService::class.java).also {
            it.setPackage(packageName)
        };
        val restartServicePendingIntent: PendingIntent = PendingIntent.getService(this, 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        applicationContext.getSystemService(Context.ALARM_SERVICE);
        val alarmService: AlarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000, restartServicePendingIntent);
    }

    private fun startService() {
        if (isServiceStarted) return
        Log.d("ES","Starting the foreground service task")
        isServiceStarted = true
        setServiceState(this, ServiceState.STARTED)

        // we need this lock so our service gets not affected by Doze Mode
        wakeLock =
                (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                    newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "EndlessService::lock").apply {
                        acquire()
                    }
                }

        // we're starting a loop in a coroutine
        GlobalScope.launch(Dispatchers.IO) {
            while (isServiceStarted) {
                delay(1 * 10 * 1000)
                launch(Dispatchers.IO) {
                    increaseUserHealth()
                }
            }
            Log.d("ES","End of the loop for the service")
        }
    }

    private fun stopService() {
        Log.d("ES","Stopping the foreground service")
        try {
            wakeLock?.let {
                if (it.isHeld) {
                    it.release()
                }
            }
            stopSelf()
        } catch (e: Exception) {
            Log.d("ES","Service stopped without being started: ${e.message}")
        }
        isServiceStarted = false
        setServiceState(this, ServiceState.STOPPED)
    }

    private fun increaseUserHealth() {

        val prefs = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

        var health = prefs.getInt("lives", -1)

        if (health < 3){
            if (health < 0)
            {
                Log.d("ES","Negative health")
            }
            else{
                Log.d("ES","increase health by timer")
                health = prefs.getInt("lives", -1)
                prefs.edit().putInt("lives", health + 1).apply()
            }
            if (prefs.getInt("lives", -1) == 3){
                Log.d("ES","health == 3")
                stopService()

            }
        }
    }
}
