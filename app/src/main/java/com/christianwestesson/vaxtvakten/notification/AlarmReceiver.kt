package com.christianwestesson.vaxtvakten.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.christianwestesson.vaxtvakten.MainActivity
import kotlinx.coroutines.newSingleThreadContext


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.i("PLANTDEBUG", "onReceive")

        val service = Intent(context, NotificationService::class.java)

        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("species", intent.getStringExtra("species"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))


        //service.data = Uri.parse("custom://" + System.currentTimeMillis())

     //   context.startService(service)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            context.startForegroundService(service)
        } else {
            context.startService(service)
        }

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