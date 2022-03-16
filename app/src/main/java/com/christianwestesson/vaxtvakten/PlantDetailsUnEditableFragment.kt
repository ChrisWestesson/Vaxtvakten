package com.christianwestesson.vaxtvakten

import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
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


class PlantDetailsUnEditableFragment : Fragment() {

    var _binding: FragmentPlantDetailsUnEditableBinding? = null
    val binding get () = _binding!!

    val model : MyPlantViewModel by activityViewModels()

    var currentPlant = MyPlant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 0,
        waterintervalHours = 0, info = "", species = "", title = "",
        wateramount = "", giveWaterDate = 0, imgName = "")


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

        var templist = PlantInfo()
            model.myplant.value!![0].let {
                templist = it
            }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")


        val observer = Observer<List<PlantInfo>> {
            var dateString = simpleDateFormat.format(currentPlant.giveWaterDate)
            binding.timeToWaterTV.text = dateString
        }
        model.myplant.observe(viewLifecycleOwner, observer)

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
        binding.myPlantDetailImageIV.setImageResource(model.stringtoIMG(currentPlant.species))
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
            model.waterMyPlant(plant = currentPlant)
            percent = model.timeLeft(
                timetowater = currentPlant.giveWaterDate,
                weeks = currentPlant.waterintervalWeeks,
                days = currentPlant.waterintervalDays,
                hours = currentPlant.waterintervalHours)
            binding.timeLeftPB.setProgress(percent)
            var dateString = simpleDateFormat.format(currentPlant.giveWaterDate)
            binding.timeToWaterTV.text = dateString
        }

        binding.editPlantDetailsButton.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragContainer, PlantDetailsFragment()).addToBackStack(null).commit()

        }


    }




}