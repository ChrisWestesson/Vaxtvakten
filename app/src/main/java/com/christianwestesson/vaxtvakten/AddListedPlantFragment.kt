package com.christianwestesson.vaxtvakten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christianwestesson.vaxtvakten.databinding.FragmentAddListedPlantBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding

class AddListedPlantFragment : Fragment() {

    var _binding: FragmentAddListedPlantBinding? = null
    val binding get () = _binding!!

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

        binding.detailsTypeTextview.text = currentPlant.species
        binding.detailsOtherTextview.text = currentPlant.info
        binding.detailsWaterAmountTextview.text = currentPlant.wateramount


        binding.homeBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragContainer, HomeFragment()).addToBackStack(null).commit()
        }
    }
}