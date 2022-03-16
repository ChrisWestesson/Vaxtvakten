package com.christianwestesson.vaxtvakten

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class MyPlantsAdapter() : RecyclerView.Adapter<MyPlantsViewHolder>() {

    lateinit var homefrag : HomeFragment


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlantsViewHolder {
        val vh = MyPlantsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recview_my_plants, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        homefrag.model.myPlantList.value?.let {
            return it.size
        }

        return 0
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyPlantsViewHolder, position: Int) {

        var currentPlant = homefrag.model.myPlantList.value!![position]
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val dateString = simpleDateFormat.format(currentPlant.giveWaterDate)

        holder.plantIMG.setImageResource(homefrag.model.stringtoIMG(currentPlant.species))


        var percent = homefrag.model.timeLeft(
            timetowater = currentPlant.giveWaterDate,
            weeks = currentPlant.waterintervalWeeks,
            days = currentPlant.waterintervalDays,
            hours = currentPlant.waterintervalHours)



        Log.i("VAXTVAKTENDEBUG", "currentPlant: ${currentPlant.toString()}")
        Log.i("VAXTVAKTENDEBUG", "currentPlant.species: ${currentPlant.species.toString()}")

        holder.species.text = currentPlant.species
        holder.progressbar.setProgress(percent)
        holder.name.text = currentPlant.title
        holder.timeleft.text = "Vattnas ${dateString}"

        holder.giveWater.setOnClickListener {
            homefrag.model.waterMyPlant(plant = currentPlant)
            homefrag.model.createMyPlantList()
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener {
            homefrag.goPlantDetails(chosenPlant = currentPlant)
        }

    }

}

class MyPlantsViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var name = view.findViewById<TextView>(R.id.myplantTitleTV)
    var species = view.findViewById<TextView>(R.id.myplantSpeciesTV)
    var progressbar = view.findViewById<ProgressBar>(R.id.myPlantsProgressBar)
    var timeleft = view.findViewById<TextView>(R.id.myPlantTimeLeftTextView)
    var giveWater = view.findViewById<TextView>(R.id.myPlantWaterTV)
    var plantIMG = view.findViewById<ImageView>(R.id.myPlantImageIV)



}