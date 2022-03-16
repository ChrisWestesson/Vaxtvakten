package com.christianwestesson.vaxtvakten

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.christianwestesson.vaxtvakten.databinding.FragmentAddListedPlantBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding

class AddListedPlantFragment : Fragment() {

    var _binding: FragmentAddListedPlantBinding? = null
    val binding get () = _binding!!
    val model : MyPlantViewModel by activityViewModels()


    var currentPlant = Plant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 0,
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
        _binding = FragmentAddListedPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("CURRENTPLANT", currentPlant.species)

        var weeks = currentPlant.waterintervalWeeks.toString()
        var days = currentPlant.waterintervalDays.toString()
        var hours = currentPlant.waterintervalHours.toString()

        binding.detailsTypeTextview.text = currentPlant.species
        binding.detailsOtherTextview.text = currentPlant.info
        binding.detailsWaterAmountTextview.text = currentPlant.wateramount
        binding.detailsFrequencyTextview.text = "${weeks} vecka ${days.toString()} dagar ${hours.toString()} timmar"


        binding.homeBtn.setOnClickListener {

            var plantToAdd = MyPlant(
                uid = 0,
                waterintervalWeeks = currentPlant.waterintervalWeeks,
                waterintervalDays = currentPlant.waterintervalDays,
                waterintervalHours = currentPlant.waterintervalHours,
                info = currentPlant.info,
                species = binding.detailsTypeTextview.text.toString(),
                title = binding.detailsNameEditText.text.toString(),
                wateramount = binding.detailsWaterAmountTextview.text.toString(),
                giveWaterDate = currentPlant.giveWaterDate,
                imgName = currentPlant.imgName)

            model.addMyPlant(plantToAdd)



            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragContainer, HomeFragment()).commit()
        }
    }
}