package com.christianwestesson.vaxtvakten

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
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
            vibrateOnClick()
            requireActivity().supportFragmentManager.beginTransaction().
            add(R.id.fragContainer, AddUnlistedPlanFragment()).commit()

        }

    }



    override fun onDestroyView() {
        super.onDestroyView()


        _binding = null
    }
    fun vibrateOnClick() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(50)
        }
    }

    fun goChoosenPlant(chosenPlant : Plant)
    {
        vibrateOnClick()
        val addlistedplantfrag = AddListedPlantFragment()
        addlistedplantfrag.currentPlant = chosenPlant

        requireActivity().supportFragmentManager.beginTransaction().
        add(R.id.fragContainer, addlistedplantfrag).addToBackStack(null).commit()
    }


    fun deleteNotification(currentplant : Plant) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Radera växt")
        builder.setMessage("Vill du verkligen radera denna fina växt?")

        builder.setPositiveButton("Radera") { dialog, which ->
            vibrateOnClick()
            model.deleteListedPlant(currentplant)
            model.createMyPlantList()
            model.createList()
            addplantadapter.notifyDataSetChanged()

        }
        builder.setNegativeButton(("Ångra")) { dialog, which ->
            vibrateOnClick()


        }

        builder.show()
    }
}
