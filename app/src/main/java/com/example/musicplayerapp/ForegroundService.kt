package com.example.musicplayerapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class ForegroundService : Service() {
    private val CHANNEL_ID = "ForegroundService Kotlin"

/*    val drawable = ContextCompat.getDrawable(this,  R.mipmap.ic_play)

    val bitmap = (drawable as BitmapDrawable?)!!.bitmap*/

    companion object {
        val FILEPATH = "filepath"

        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context, startIntent)

        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, ForegroundService::class.java)
            context.stopService(stopIntent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // TODO: Step 2.0 add style
           //do heavy work on a background thread
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)

        val playIntent = Intent(this, MainActivity::class.java)


        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val prevPendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val playPendingIntent = PendingIntent.getActivity(
            this,
            0, playIntent, 0
        )
        val nextPendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("My Music Player")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_play)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_prev_foreground, "Previous", prevPendingIntent) // #0
            .addAction(R.drawable.ic_play_m, "Play", playPendingIntent) // #1
            .addAction(R.drawable.ic_next_foreground, "Next", nextPendingIntent) // #2
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_play))
            .build()


        startForeground(1, notification)
        //stopSelf();
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}


