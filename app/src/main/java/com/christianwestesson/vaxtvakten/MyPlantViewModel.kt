package com.christianwestesson.vaxtvakten

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.*
import java.util.*

class MyPlantViewModel : ViewModel() {

    val myplant: MutableLiveData<List<PlantInfo>> by lazy {
        MutableLiveData<List<PlantInfo>>()
    }

    val plantList: MutableLiveData<List<Plant>> by lazy {
        MutableLiveData<List<Plant>>()
    }

    val myPlantList: MutableLiveData<List<MyPlant>> by lazy {
        MutableLiveData<List<MyPlant>>()
    }

    val listofplants = mutableListOf<PlantInfo>()



    var newTimetoWaterMilli : Long? = null
    var timeInMillis : Long? = null
    var intervalMilli : Int? = null

  //  val progressPercent : Int? = null

    val progressPercent: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }



    fun createList() {
        Log.i("VAXTVAKTENDEBUG", "Createlist körs")

        var userdao = Databasehelper.getDatabase().userDao()
        var list = userdao.getAll()
        val listofPlants = mutableListOf<Plant>()

        for(item in list) {
            listofPlants.add(item)
        }


        plantList.value = listofPlants
        Log.i("VAXTVAKTENDEBUG", "model.plantList.value: ${plantList.value!!.toString()}")

    }

    fun createMyPlantList() {
        Log.i("VAXTVAKTENDEBUG", "Createlist körs")

        var userdao = Databasehelper.getDatabase().userDao()
        var list = userdao.getAllMyPlants()
        val myListofPlants = mutableListOf<MyPlant>()

        for(item in list) {
            myListofPlants.add(item)
        }


        myPlantList.value = myListofPlants
        Log.i("VAXTVAKTENDEBUG", "model.plantList.value: ${myPlantList.value!!.toString()}")

    }

    fun addMyPlant(plantinfo : MyPlant) {
        Log.i("VAXTVAKTENDEBUG", "addMyPlant körs!")
        var userdao = Databasehelper.getDatabase().userDao()
        var date = Calendar.getInstance().timeInMillis

        var myPlant = MyPlant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 3,
            waterintervalHours = 12, info = "", species = "Aralia", title = "Min växt",
            wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "aralia")

        Log.i("VAXTVAKTENDEBUG", "insertmyplant")
        userdao.insertMyPlant(plantinfo)


        Log.i("VAXTVAKTENDEBUG", "Get all my plants: ${userdao.getAllMyPlants().toString()}")
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
    fun wateringDate (weeks : Int , days : Int, hours : Int ) {

        Log.i("VAXTVAKTENDEBUG", "wateringdate körs")

        var weeksinMilli = weeks * 604800
        var daysinMilli = days * 86400
        var hoursMilli = hours * 3600

        intervalMilli = weeksinMilli + daysinMilli + hoursMilli



        timeInMillis = Calendar.getInstance().timeInMillis


        Log.i("VAXTVAKTENDEBUG", "timeinMillis : ${timeInMillis}")

        var calendar = Calendar.getInstance()

        Log.i("VAXTVAKTENDEBUG", "calendar : ${calendar.time}")

        calendar.add(calendar.weekYear, + weeks)
        calendar.add(Calendar.DAY_OF_YEAR, +days)
        calendar.add(Calendar.HOUR, +hours)

        newTimetoWaterMilli = calendar.timeInMillis
        var newtime = newTimetoWaterMilli

        var newTimetoWater = calendar.time

        waterPlant(newTimetoWater)





        Log.i("VAXTVAKTENDEBUG", "calendar after add. : ${calendar.time}")



        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val dateString = simpleDateFormat.format(newTimetoWater)


        Log.i("VAXTVAKTENDEBUG", "dateString : ${dateString}")

        //var percent = timeLeft(newtime!!)

    }

    fun waterMyPlant(plant : MyPlant) {
        var wateredPlant = plant

        var timeToWater = Calendar.getInstance()

        Log.i("VAXTVAKTENDEBUG", "timetowater : ${timeToWater.time}")

        timeToWater.add(Calendar.WEEK_OF_YEAR, + plant.waterintervalWeeks)
        timeToWater.add(Calendar.DAY_OF_YEAR, + plant.waterintervalDays)
        timeToWater.add(Calendar.HOUR, + plant.waterintervalHours)

        var timetoWaterMilli = timeToWater.timeInMillis

        wateredPlant.giveWaterDate = timetoWaterMilli

        var userdao = Databasehelper.getDatabase().userDao()
        userdao.updateWaterDate(plant = wateredPlant)


    }

    fun timeLeft(timetowater : Long, weeks : Int, days : Int, hours : Int) : Int {
        var weeksinMilli = weeks * 604800
        var daysinMilli = days * 86400
        var hoursMilli = hours * 3600

        var intervalMilli = weeksinMilli + daysinMilli + hoursMilli

        timeInMillis = Calendar.getInstance().timeInMillis
        //var timeleftMilli = newTimetoWaterMilli!! - timeInMillis!!

        var timeleftMilli = timetowater - timeInMillis!!

        /*
        var timeleftMilli : Long =  1

        if(timetowater > timeInMillis!!) {
            timeleftMilli = timetowater - timeInMillis!!
        }

         */

        Log.i("VAXTVAKTENDEBUG", "timeinmillis: ${timeInMillis}")
        Log.i("VAXTVAKTENDEBUG", "timetowater: ${timetowater}")
        Log.i("VAXTVAKTENDEBUG", "timeleftMilli: ${timeleftMilli}")

        var timeleftpercent = timeleftMilli / intervalMilli!! * 0.1
        Log.i("VAXTVAKTENDEBUG", "timeleftpercent: ${timeleftpercent}")

        progressPercent.value = timeleftpercent.toInt()

        return timeleftpercent.toInt()


    }




}