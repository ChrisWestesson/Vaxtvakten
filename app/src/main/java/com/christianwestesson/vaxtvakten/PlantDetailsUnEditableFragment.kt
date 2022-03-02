package com.christianwestesson.vaxtvakten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christianwestesson.vaxtvakten.databinding.FragmentAddListedPlantBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentPlantDetailsBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentPlantDetailsUnEditableBinding


class PlantDetailsUnEditableFragment : Fragment() {

    var _binding: FragmentPlantDetailsUnEditableBinding? = null
    val binding get () = _binding!!


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editPlantDetailsButton.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragContainer, PlantDetailsFragment()).addToBackStack(null).commit()

        }

        binding.waterPlantButton.setOnClickListener {

        }
    }




}