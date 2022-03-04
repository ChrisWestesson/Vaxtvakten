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

        model.addPlant()

        var templist = PlantInfo()
            model.myplant.value!![0].let {
                templist = it
            }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")


        val observer = Observer<List<PlantInfo>> {
            var dateString = simpleDateFormat.format(templist.giveWaterDate)
            binding.timeToWaterTV.text = dateString
        }
        model.myplant.observe(viewLifecycleOwner, observer)

        val observerProgress = Observer<Int> {
            binding.timeLeftPB.setProgress(it, true)

        }
        model.progressPercent.observe(viewLifecycleOwner, observerProgress)



        //model.wateringDate (1, 12)

        var days = templist.waterintervalDays!!
        var hours = templist.waterintervalHours!!

        Log.i("VAXTVAKTENDEBUG", "hours: ${hours}")
        Log.i("VAXTVAKTENDEBUG", "dayss: ${days}")



        binding.frekvensTV.text = "${days.toString()} dagar ${hours.toString()} timmar"

        binding.giveWaterButton.setOnClickListener {
            model.wateringDate(days, hours)
        }

        binding.editPlantDetailsButton.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragContainer, PlantDetailsFragment()).addToBackStack(null).commit()

        }

        binding.waterPlantButton.setOnClickListener {

            model.timeLeft()

        }
    }




}