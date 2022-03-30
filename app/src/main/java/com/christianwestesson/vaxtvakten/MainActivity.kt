package com.christianwestesson.vaxtvakten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.christianwestesson.vaxtvakten.notification.NotificationUtils
import java.util.*


/*
Lägga till egen växt --> Måste ange Art
                            Kunna ta bort blommor från listan.
                            Popback istället för replace?



 */


class MainActivity : AppCompatActivity() {
//notification
    var ten = 10000
    var twenty = 20000

    private val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
    private val mNotificationTime2 = Calendar.getInstance().timeInMillis + ten //Set after 5 seconds from the current time.
    private val mNotificationTime3 = Calendar.getInstance().timeInMillis + twenty //Set after 5 seconds from the current time.
    private var mNotified = false

    //notification

    val homeFrag = HomeFragment()
    val addPlantList = AddPlantListFragment()
    val plantDetail = PlantDetailsFragment()
    val plantDetailUnE = PlantDetailsUnEditableFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        Databasehelper.ctx = applicationContext

        setContentView(R.layout.activity_main)

        Databasehelper.checkStart()




        supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()
        //val fragcont = findViewById<FragmentContainerView>(R.id.fragContainer)
/*
        findViewById<Button>(R.id.mainActivityGoAddPlantListBtn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, addPlantList).commit()
        }

        findViewById<Button>(R.id.mainActivityGoHomeBtnn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()
        }

 */
        findViewById<ImageButton>(R.id.homePlantBtn).setOnClickListener {
           // NotificationUtils().setNotification(mNotificationTime2, this@MainActivity)
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()
        }
        findViewById<ImageButton>(R.id.addPlantBtn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, addPlantList).commit()

        }

        findViewById<ImageButton>(R.id.notificationPlantBtn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, plantDetailUnE).commit()

        }

        findViewById<ImageButton>(R.id.deletePlantBtn).setOnClickListener {

            if (homeFrag.model.homeFragment.showDeleteButton == true)
            {
                homeFrag.model.homeFragment.showDeleteButton = false

            } else {
                homeFrag.model.homeFragment.showDeleteButton = true

            }
            homeFrag.myPlantsAdapter.notifyDataSetChanged()
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()


        }

        //test github
        // Mattias was here
        // Luc says hello!
        //Nytt test CW
        //Mattias was here again
    }

}