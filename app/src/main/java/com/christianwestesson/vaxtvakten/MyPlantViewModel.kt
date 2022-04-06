package com.christianwestesson.vaxtvakten

import android.app.AlertDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.*
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MyPlantViewModel : ViewModel() {

    var homeFragment = HomeFragment()

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




    fun stringtoIMG(name : String) : Int{
        var image = 0
        when (name) {
            "Amaryllis" -> image = R.drawable.amaryllis
            "Aralia" -> image = R.drawable.aralia
            "Ampellilja" -> image = R.drawable.ampellilja
            "Aspidistra" -> image = R.drawable.aspidistra
            "Begonia" -> image = R.drawable.begonia
            "Benjaminfikus" -> image = R.drawable.benjaminfikus
            "Cyklamen" -> image = R.drawable.cyklamen
            "Flitiga Lisa" -> image = R.drawable.flitigalisa
            "Fredskalla" -> image = R.drawable.fredskalla
            "Gloxinia" -> image = R.drawable.gloxinia
            "Hemtrevnad" -> image = R.drawable.hemtrevnad
            "Julstjärna" -> image = R.drawable.julstjarna
            "Monstera" -> image = R.drawable.monstera
            "Paradisträd" -> image = R.drawable.paradistrad
            "Pelargon" -> image = R.drawable.pelargon
            "Prickblad" -> image = R.drawable.prickblad
            "Silverkalla" -> image = R.drawable.silverkalla
            "Spjutbräken" -> image = R.drawable.spjutbraken
            "Svärmors kudde" -> image = R.drawable.svarmorskudde
            "Svärmors tunga" -> image = R.drawable.svarmorstunga
            "Skvallerreva" -> image = R.drawable.skvallerreva
            "Våreld" -> image = R.drawable.vareld


            else -> {
                image = R.drawable.ic_baseline_local_florist_24
            }
        }
        return  image
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

        myPlantList.value = null

        var userdao = Databasehelper.getDatabase().userDao()
        var list = userdao.getAllMyPlants()
        val myListofPlants = mutableListOf<MyPlant>()

        for(item in list) {
            myListofPlants.add(item)
        }


        myPlantList.value = myListofPlants
        Log.i("VAXTVAKTENDEBUG", "model.plantList.value: ${myPlantList.value!!.toString()}")
        homeFragment.myPlantsAdapter.notifyDataSetChanged()

    }

    fun addMyPlant(plantinfo : MyPlant) {
        Log.i("VAXTVAKTENDEBUG", "addMyPlant körs!")
        var userdao = Databasehelper.getDatabase().userDao()
        var date = Calendar.getInstance().timeInMillis


        Log.i("VAXTVAKTENDEBUG", "insertmyplant")
        userdao.insertMyPlant(plantinfo)

    }

    fun deleteMyPlant(plantinfo : MyPlant) {

        Log.i("VAXTVAKTENDEBUG", "deleteMyPlant körs!")
        var userdao = Databasehelper.getDatabase().userDao()

        Log.i("VAXTVAKTENDEBUG", "deleteMyplant")
        userdao.deleteMyPlant(plantinfo)
    }

    fun deleteListedPlant(plantinfo : Plant) {
        Log.i("VAXTVAKTENDEBUG", "deleteMyPlant körs!")
        var userdao = Databasehelper.getDatabase().userDao()

        Log.i("VAXTVAKTENDEBUG", "deleteListedPlant")
        userdao.deletePlant(plantinfo)
    }






    fun addPlant(plantinfo : Plant) {

        Log.i("VAXTVAKTENDEBUG", "addPlant körs!")
        var userdao = Databasehelper.getDatabase().userDao()
        var date = Calendar.getInstance().timeInMillis


        Log.i("VAXTVAKTENDEBUG", "insertplant")
        userdao.insertPlant(plantinfo)

    }

    fun deletePlant(plantinfo : Plant) {
        Log.i("VAXTVAKTENDEBUG", "deleteMyPlant körs!")
        var userdao = Databasehelper.getDatabase().userDao()
        var date = Calendar.getInstance().timeInMillis


        Log.i("VAXTVAKTENDEBUG", "delete")

        userdao.deletePlant(plantinfo)

    }

    /*
    fun waterPlant (nextDate : Date) {
        Log.i("VAXTVAKTENDEBUG", "waterplant körs")
       var currentPlant = myplant.value!![0]
        currentPlant.giveWaterDate = nextDate

        myplant.value = listOf(currentPlant)

        Log.i("VAXTVAKTENDEBUG", "myplant.value : ${myplant.value!!}")

    }

     */


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

        //waterPlant(newTimetoWater)





        Log.i("VAXTVAKTENDEBUG", "calendar after add. : ${calendar.time}")



        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val dateString = simpleDateFormat.format(newTimetoWater)


        Log.i("VAXTVAKTENDEBUG", "dateString : ${dateString}")

        //var percent = timeLeft(newtime!!)

    }

    fun waterMyPlant(plant : MyPlant) {
        var wateredPlant = plant

        var timeToWater = Calendar.getInstance()

        Log.i("VAXTVAKTENDEBUG", "timetowater before added: ${timeToWater.time} " +timeToWater.timeInMillis)


        timeToWater.add(Calendar.WEEK_OF_YEAR, + plant.waterintervalWeeks)
        timeToWater.add(Calendar.DAY_OF_YEAR, + plant.waterintervalDays)
        timeToWater.add(Calendar.HOUR, + plant.waterintervalHours)

        var timetoWaterMilli = timeToWater.timeInMillis





       // var timetoWaterMilli = timeToWater.timeInMillis + 5000

        Log.i("VAXTVAKTENDEBUG", "timetowater after added: ${timeToWater.time} " +timetoWaterMilli)

        wateredPlant.giveWaterDate = timetoWaterMilli

        var userdao = Databasehelper.getDatabase().userDao()
        userdao.updateWaterDate(plant = wateredPlant)

    }

    fun updateMyPlant(myPlant : MyPlant) {
        /*
        var wateredPlant = plant

        var timeToWater = Calendar.getInstance()

        Log.i("VAXTVAKTENDEBUG", "timetowater : ${timeToWater.time}")

        //timeToWater.add(Calendar.WEEK_OF_YEAR, + plant.waterintervalWeeks)
        //  timeToWater.add(Calendar.DAY_OF_YEAR, + plant.waterintervalDays)
        //  timeToWater.add(Calendar.HOUR, + plant.waterintervalHours)

        // var timetoWaterMilli = timeToWater.timeInMillis

        var timetoWaterMilli = timeToWater.timeInMillis + 5000

        wateredPlant.giveWaterDate = timetoWaterMilli

         */

        var userdao = Databasehelper.getDatabase().userDao()
        userdao.updateWaterDate(plant = myPlant)

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
        var timeleftpercent = 0.1

        if (timeleftMilli > 0) {
            timeleftpercent = timeleftMilli / intervalMilli!! * 0.1
        }

        Log.i("VAXTVAKTENDEBUG", "timeleftpercent: ${timeleftpercent}")

        progressPercent.value = timeleftpercent.toInt()

        return timeleftpercent.toInt()


    }






}