package com.christianwestesson.vaxtvakten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentContainerView
import java.util.*

class MainActivity : AppCompatActivity() {

    var ten = 10000
    var twenty = 20000

    private val mNotificationTime = Calendar.getInstance().timeInMillis + 2000//5000 //Set after 5 seconds from the current time.
    private val mNotificationTime2 = Calendar.getInstance().timeInMillis + ten //Set after 5 seconds from the current time.
    private val mNotificationTime3 = Calendar.getInstance().timeInMillis + twenty //Set after 5 seconds from the current time.
    private var mNotified = false

    val homeFrag = HomeFragment()
    val addPlantList = AddPlantListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()
        //val fragcont = findViewById<FragmentContainerView>(R.id.fragContainer)

        findViewById<Button>(R.id.mainActivityGoAddPlantListBtn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, addPlantList).commit()
        }

        findViewById<Button>(R.id.mainActivityGoHomeBtn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()
        }

        findViewById<Button>(R.id.notifyButton).setOnClickListener {

            NotificationUtils().setNotification(mNotificationTime, this@MainActivity)
            NotificationUtils().setNotification(mNotificationTime2, this@MainActivity)
            NotificationUtils().setNotification(mNotificationTime3, this@MainActivity)

        }

        if (!mNotified) {
            // NotificationUtils().setNotification(mNotificationTime, this@MainActivity)
        }

        //test github
        // Mattias was here
        // Luc says hello!
        //Nytt test CW
        //Mattias was here again
    }

}