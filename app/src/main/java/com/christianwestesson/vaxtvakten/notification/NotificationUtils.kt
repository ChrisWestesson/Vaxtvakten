package com.christianwestesson.vaxtvakten.notification



import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.*

/**
 * Created by sky on 5/12/17.
 */

class NotificationUtils {


    fun setNotification(timeInMilliSeconds: Long, activity: Activity, id : Int, plantname : String, species : String) {

        //------------  alarm settings start  -----------------//

        Log.i("VAXTAPPDEBUG", "setNotification körs, timeinmillis: ${timeInMilliSeconds}, activity: ${activity}")

        if (timeInMilliSeconds > 0) {


            val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java) // AlarmReceiver1 = broadcast receiver



            alarmIntent.putExtra("reason", plantname)
            alarmIntent.putExtra("plant", plantname)
            alarmIntent.putExtra("species", plantname)
            alarmIntent.putExtra("timestamp", timeInMilliSeconds)






            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMilliSeconds


            val pendingIntent = PendingIntent.getBroadcast(activity, id, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)







        }

        //------------ end of alarm settings  -----------------//


    }
}