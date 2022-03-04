package com.christianwestesson.vaxtvakten

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.util.*

class MyPlantViewModel : ViewModel() {

    val myplant: MutableLiveData<List<PlantInfo>> by lazy {
        MutableLiveData<List<PlantInfo>>()
    }

    val listofplants = mutableListOf<PlantInfo>()

    var newTimetoWaterMilli : Long? = null
    var timeInMillis : Long? = null
    var intervalMilli : Int? = null

  //  val progressPercent : Int? = null

    val progressPercent: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }



    fun addPlant() {

        var temp = PlantInfo()
        temp.title = "Rosorna i hallen"
        temp.info = "Ska stå i skugga"
        temp.species = "Rosor"
        temp.wateramount = "1 dl"
        temp.waterintervalDays = 0
        temp.waterintervalHours = 1
        temp.giveWaterDate = Calendar.getInstance().time


        listofplants.add(temp)

        myplant.value = listofplants
    }

    fun waterPlant (nextDate : Date) {
        Log.i("VAXTVAKTENDEBUG", "waterplant körs")
       var currentPlant = myplant.value!![0]
        currentPlant.giveWaterDate = nextDate

        myplant.value = listOf(currentPlant)

        Log.i("VAXTVAKTENDEBUG", "myplant.value : ${myplant.value!!}")

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun wateringDate (days : Int, hours : Int ) {

        Log.i("VAXTVAKTENDEBUG", "wateringdate körs")

        var daysinMilli = days * 86400
        var hoursMilli = hours * 3600

        intervalMilli = daysinMilli + hoursMilli



        timeInMillis = Calendar.getInstance().timeInMillis


        Log.i("VAXTVAKTENDEBUG", "timeinMillis : ${timeInMillis}")

        var calendar = Calendar.getInstance()

        Log.i("VAXTVAKTENDEBUG", "calendar : ${calendar.time}")

        calendar.add(Calendar.DAY_OF_YEAR, +days)
        calendar.add(Calendar.HOUR, +hours)

        newTimetoWaterMilli = calendar.timeInMillis

        var newTimetoWater = calendar.time

        waterPlant(newTimetoWater)





        Log.i("VAXTVAKTENDEBUG", "calendar after add. : ${calendar.time}")



        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val dateString = simpleDateFormat.format(newTimetoWater)


        Log.i("VAXTVAKTENDEBUG", "dateString : ${dateString}")

        timeLeft()

    }

    fun timeLeft() {
        timeInMillis = Calendar.getInstance().timeInMillis
        var timeleftMilli = newTimetoWaterMilli!! - timeInMillis!!
        Log.i("VAXTVAKTENDEBUG", "newtimetowater: ${newTimetoWaterMilli}")
        Log.i("VAXTVAKTENDEBUG", "timeinmillis: ${timeleftMilli}")
        Log.i("VAXTVAKTENDEBUG", "timeleftMilli: ${timeleftMilli}")

        var timeleftpercent = timeleftMilli / intervalMilli!! * 0.1
        Log.i("VAXTVAKTENDEBUG", "timeleftpercent: ${timeleftpercent}")

        progressPercent.value = timeleftpercent.toInt()

    }
}