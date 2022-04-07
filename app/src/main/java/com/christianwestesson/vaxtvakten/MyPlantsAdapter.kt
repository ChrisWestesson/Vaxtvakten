package com.christianwestesson.vaxtvakten

import android.graphics.BitmapFactory
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
import com.christianwestesson.vaxtvakten.notification.NotificationUtils
import java.io.File
import java.util.*

class MyPlantsAdapter() : RecyclerView.Adapter<MyPlantsViewHolder>() {
    var ten = 10000
    var twenty = 20000

    private val mNotificationTime2 = Calendar.getInstance().timeInMillis + 5000
    private var mNotified = false


    lateinit var homefrag: HomeFragment


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlantsViewHolder {
        val vh = MyPlantsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recview_my_plants, parent, false)
        )
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

        // holder.plantIMG.setImageResource(homefrag.model.stringtoIMG(currentPlant.species))
        //  holder.plantIMG.setImageResource(R.drawable.)

        if (currentPlant.userimgName != "") {
            // GET FILE STUFF
            val imagedir =
                File(holder.itemView.context.getExternalFilesDir("vaxtvakten"), "plantimages")
            val imagefile = File(imagedir, currentPlant.userimgName)

            val imagebytes = imagefile.readBytes()
            val bitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.size)
            holder.plantIMG.setImageBitmap(bitmap)
        } else {
            holder.plantIMG.setImageResource(homefrag.model.stringtoIMG(currentPlant.species))
        }

        var percent = homefrag.model.timeLeft(
            timetowater = currentPlant.giveWaterDate,
            weeks = currentPlant.waterintervalWeeks,
            days = currentPlant.waterintervalDays,
            hours = currentPlant.waterintervalHours
        )



        Log.i("VAXTVAKTENDEBUG", "currentPlant: ${currentPlant.toString()}")
        Log.i("VAXTVAKTENDEBUG", "currentPlant.species: ${currentPlant.species.toString()}")

        holder.species.text = currentPlant.species
        holder.progressbar.setProgress(percent)
        holder.name.text = currentPlant.title
       // holder.timeleft.text = "Vattnas ${dateString}"


        holder.waterPlant.setOnClickListener {
            homefrag.vibrateOnClick()
            Log.i("VAXTAPPDEBUG","klickat på vattna, time in millis INNAN watermyplant: " + currentPlant.giveWaterDate)

            homefrag.model.waterMyPlant(plant = currentPlant)
            homefrag.model.createMyPlantList()


            NotificationUtils().setNotification(currentPlant.giveWaterDate, homefrag.requireActivity(), id = currentPlant.uid, plantname = currentPlant.title, species = currentPlant.species)

            Log.i("VAXTAPPDEBUG","klickat på vattna, time in millis EFTER watermyplant: " + currentPlant.giveWaterDate + "activity: " + homefrag.requireActivity().toString())

            notifyDataSetChanged()


        }

            holder.itemView.setOnClickListener {
                homefrag.vibrateOnClick()
                homefrag.goPlantDetails(chosenPlant = currentPlant)




            }
        if (homefrag.model.homeFragment.showDeleteButton == false){
            holder.deleteIMG.visibility = View.INVISIBLE
        } else if (homefrag.model.homeFragment.showDeleteButton == true){
            holder.deleteIMG.visibility = View.VISIBLE
        }



        holder.deleteIMG.setOnClickListener {
            homefrag.vibrateOnClick()
            homefrag.deleteNotification(currentPlant)

        }

        }

    }


    class MyPlantsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.findViewById<TextView>(R.id.myplantTitleTV)
        var species = view.findViewById<TextView>(R.id.myplantSpeciesTV)
        var progressbar = view.findViewById<ProgressBar>(R.id.myPlantsProgressBar)
        //var timeleft = view.findViewById<TextView>(R.id.myPlantTimeLeftTextView)
        var waterPlant = view.findViewById<ImageView>(R.id.imageButton)
        var giveWater = view.findViewById<TextView>(R.id.myPlantWaterTV)
        var plantIMG = view.findViewById<ImageView>(R.id.myPlantImageIV)
        var deleteIMG = view.findViewById<ImageView>(R.id.myPlantRedDelete)


    }
