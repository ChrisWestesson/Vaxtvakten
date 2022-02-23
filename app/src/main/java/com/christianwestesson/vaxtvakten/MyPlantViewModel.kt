package com.christianwestesson.vaxtvakten

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPlantViewModel : ViewModel() {

    val myplant: MutableLiveData<List<PlantInfo>> by lazy {
        MutableLiveData<List<PlantInfo>>()
    }

    val listofplants = mutableListOf<PlantInfo>()



    fun addPlant() {
        var temp = PlantInfo()
        temp.title = "Rosorna i hallen"
        temp.info = "Ska stå i skugga och är känsliga för övervattning. Tål inte direkt solljus."
        temp.species = "Rosor"
        temp.wateramount = "1 dl"
        temp.waterinterval = 300000

        listofplants.add(temp)

        myplant.value = listofplants
    }
}