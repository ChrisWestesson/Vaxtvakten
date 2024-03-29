package com.christianwestesson.vaxtvakten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.christianwestesson.vaxtvakten.databinding.FragmentAddListedPlantBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentPlantDetailsBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentPlantDetailsUnEditableBinding
import com.christianwestesson.vaxtvakten.notification.NotificationUtils
import com.google.android.material.snackbar.Snackbar
import java.io.File


class PlantDetailsUnEditableFragment : Fragment() {

    var _binding: FragmentPlantDetailsUnEditableBinding? = null
    val binding get () = _binding!!

    val model : MyPlantViewModel by activityViewModels()

    var currentPlant = MyPlant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 0,
        waterintervalHours = 0, info = "", species = "", title = "",
        wateramount = "", giveWaterDate = 0, imgName = "", userimgName = "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_plant_details_un_editable, container, false)
        _binding = FragmentPlantDetailsUnEditableBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // model.addPlant()

        /*
        var templist = PlantInfo()
            model.myplant.value!![0].let {
                templist = it
            }
         */

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:00")

        val dateString = simpleDateFormat.format(currentPlant.giveWaterDate)
        binding.timeToWaterTV.text = dateString


        /*
        val observer = Observer<List<PlantInfo>> {
            var dateString = simpleDateFormat.format(currentPlant.giveWaterDate)
            binding.timeToWaterTV.text = dateString
        }
        model.myplant.observe(viewLifecycleOwner, observer)

         */

       /* val observerProgress = Observer<Int> {
            binding.timeLeftPB.setProgress(it, true)

        }

        */

        var percent = model.timeLeft(
            timetowater = currentPlant.giveWaterDate,
            weeks = currentPlant.waterintervalWeeks,
            days = currentPlant.waterintervalDays,
            hours = currentPlant.waterintervalHours)
        binding.timeLeftPB.setProgress(percent)

        var days = currentPlant.waterintervalDays
        var hours = currentPlant.waterintervalHours
        var weeks = currentPlant.waterintervalWeeks

        //model.progressPercent.observe(viewLifecycleOwner, observerProgress)
        if(currentPlant.userimgName != "")
        {
            // GET FILE STUFF
            val imagedir = File(requireContext().getExternalFilesDir("vaxtvakten"), "plantimages")
            val imagefile = File(imagedir, currentPlant.userimgName)

            val imagebytes = imagefile.readBytes()
            val bitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.size)
            binding.myPlantDetailImageIV.setImageBitmap(bitmap)
        } else {
            binding.myPlantDetailImageIV.setImageResource(model.stringtoIMG(currentPlant.species))
        }


       // binding.myPlantDetailImageIV.setImageResource(model.stringtoIMG(currentPlant.species))
        binding.detailsNameTextview.text = currentPlant.title
        binding.detailsTypeTextview.text = currentPlant.species
        binding.detailsWaterAmountTextview.text = currentPlant.wateramount
        binding.detailsFrequencyTextview.text =  "${weeks.toString()} vecka ${days.toString()} dagar ${hours.toString()} timmar"
        binding.detailsOtherTextview.text = currentPlant.info




        //model.wateringDate (1, 12)



        Log.i("VAXTVAKTENDEBUG", "hours: ${hours}")
        Log.i("VAXTVAKTENDEBUG", "dayss: ${days}")



       // binding.frekvensTV.text = "${days.toString()} dagar ${hours.toString()} timmar"

        binding.giveWaterButton.setOnClickListener {
            vibrateOnClick()
            Snackbar.make(requireView(), ("Du har vattnat din " + currentPlant.species), Snackbar.LENGTH_SHORT).show()
            model.waterMyPlant(plant = currentPlant)
            percent = model.timeLeft(
                timetowater = currentPlant.giveWaterDate,
                weeks = currentPlant.waterintervalWeeks,
                days = currentPlant.waterintervalDays,
                hours = currentPlant.waterintervalHours)
            binding.timeLeftPB.setProgress(percent)
            var dateString = simpleDateFormat.format(currentPlant.giveWaterDate)
            binding.timeToWaterTV.text = dateString

            NotificationUtils().setNotification(currentPlant.giveWaterDate, requireActivity(), id = currentPlant.uid, plantname = currentPlant.title, species = currentPlant.species)

            model.createMyPlantList()
        }

        binding.editPlantDetailsButton.setOnClickListener {
            vibrateOnClick()

            val plantdetailsfrag = PlantDetailsFragment()
            plantdetailsfrag.currentPlant = currentPlant

            requireActivity().supportFragmentManager.beginTransaction().
            add(R.id.fragContainer, plantdetailsfrag).addToBackStack(null).commit()


        }


    }
    fun vibrateOnClick() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(50)
        }
    }




}