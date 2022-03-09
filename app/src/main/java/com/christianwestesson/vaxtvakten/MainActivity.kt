package com.christianwestesson.vaxtvakten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {

    val homeFrag = HomeFragment()
    val addPlantList = AddPlantListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Databasehelper.ctx = applicationContext

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()
        //val fragcont = findViewById<FragmentContainerView>(R.id.fragContainer)

        findViewById<Button>(R.id.mainActivityGoAddPlantListBtn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, addPlantList).commit()
        }

        findViewById<Button>(R.id.mainActivityGoHomeBtn).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, homeFrag).commit()
        }

        //test github
        // Mattias was here
        // Luc says hello!
        //Nytt test CW
        //Mattias was here again
    }
}


