package com.christianwestesson.vaxtvakten

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.christianwestesson.vaxtvakten.databinding.ActivityMainBinding.inflate
import com.christianwestesson.vaxtvakten.databinding.FragmentAddListedPlantBinding.inflate
import com.christianwestesson.vaxtvakten.databinding.FragmentAddPlantListBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding

class AddPlantListFragment : Fragment() {

    val addplantadapter = AddPlantAdapter()
    val model : MyPlantViewModel by activityViewModels()



    var _binding: FragmentAddPlantListBinding? = null
    val binding get () = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addplantadapter.addplantfrag = this

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_plant_list, container, false)
        _binding = FragmentAddPlantListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var addplantRecview = view.findViewById<RecyclerView>(R.id.addPlantRV)

        addplantRecview.layoutManager = LinearLayoutManager(requireContext())
        addplantRecview.adapter = addplantadapter

        binding.addNewPlantButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().
            add(R.id.fragContainer, AddUnlistedPlanFragment()).addToBackStack(null).commit()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()


        _binding = null
    }

    fun goChoosenPlant(chosenPlant : Plant)
    {
        val addlistedplantfrag = AddListedPlantFragment()
        addlistedplantfrag.currentPlant = chosenPlant

        requireActivity().supportFragmentManager.beginTransaction().
        add(R.id.fragContainer, addlistedplantfrag).addToBackStack(null).commit()
    }
}
