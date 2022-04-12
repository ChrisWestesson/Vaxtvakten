package com.christianwestesson.vaxtvakten.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.christianwestesson.vaxtvakten.MainActivity
import com.christianwestesson.vaxtvakten.R


class AlarmReceiver : BroadcastReceiver() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    override fun onReceive(context: Context, intent: Intent) {

        Log.i("PLANTDEBUG", "onReceive")

        var title = intent.getStringExtra("reason") + " (" + intent.getStringExtra("species") + ")"
        var message = "Jag behöver vatten!"
        var plantid = intent.getIntExtra("plantid", 1)

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // pendingIntent is an intent for future use i.e after
        // the notification is clicked, this intent will come into action
        val mainintent = Intent(context, MainActivity::class.java)
        mainintent.putExtra("plantid", plantid)

        // FLAG_UPDATE_CURRENT specifies that if a previous
        // PendingIntent already exists, then the current one
        // will update it with the latest intent
        // 0 is the request code, using it later with the
        // same method again will get back the same pending
        // intent for future reference
        // intent passed here is to our afterNotification class
        val pendingIntent = PendingIntent.getActivity(context, 0, mainintent, PendingIntent.FLAG_UPDATE_CURRENT)

        // RemoteViews are used to use the content of
        // some different layout apart from the current activity layout
        //val contentView = RemoteViews(packageName, R.layout.activity_after_notification)


        // checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel("a", "b", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(context, "a")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setStyle(Notification.BigTextStyle()
                    .bigText(message))
                .setContentText(message)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setStyle(Notification.BigTextStyle()
                    .bigText(message))
                .setContentText(message)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(plantid, builder.build())



        /*
        var builder = NotificationCompat.Builder(context, "a")
            .setSmallIcon(R.drawable.ic_baseline_local_florist_24)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        */

        /*
        val service = Intent(context, NotificationService::class.java)

        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("species", intent.getStringExtra("species"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))
        */



        //service.data = Uri.parse("custom://" + System.currentTimeMillis())

        //context.startService(service)
        //context.startForegroundService(service)

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //context.startForegroundService(service)
            val NOTIFICATION_CHANNEL_ID = "com.example.simpleapp"
            val channelName = "My Background Service"
            val chan = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                channelName,
                NotificationManager.IMPORTANCE_NONE
            )
            chan.lightColor = Color.BLUE
            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?)!!
            manager.createNotificationChannel(chan)
            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            val notification: Notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_baseline_local_florist_24)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build()
            context.startForegroundService()
            startForeground(2, notification)
        } else {
            context.startService(service)
        }
        */
        /*






        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Log.i("ALARMDEBUG", "Intent.ACTION_BOOT_COMPLETED == intent.action == true")
            val intent = Intent(context, NotificationService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(service)
            } else {
                context.startService(service)
            }
            Log.i("Autostart", "started")
        } else {
            Log.i("ALARMDEBUG", "Intent.ACTION_BOOT_COMPLETED == intent.action == FALSE")
        }
 */









    }



}