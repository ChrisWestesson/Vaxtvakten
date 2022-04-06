package com.christianwestesson.vaxtvakten

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding? = null
    val binding get () = _binding!!
    val model : MyPlantViewModel by activityViewModels()
    var myPlantsAdapter = MyPlantsAdapter()
    var showDeleteButton = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myPlantsAdapter.homefrag = this



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myPlantsRecView = view.findViewById<RecyclerView>(R.id.myPlantsRV)
        myPlantsRecView.layoutManager = GridLayoutManager(activity, 2)
        myPlantsRecView.adapter = myPlantsAdapter

        model.createList()
        model.createMyPlantList()
        myPlantsAdapter.notifyDataSetChanged()

        if (model.myPlantList.value?.size ?: 0 == 0) {
            binding.noPlantsTextView.visibility = View.VISIBLE
            binding.noPlantAddButton.visibility = View.VISIBLE

            binding.noPlantAddButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragContainer, AddPlantListFragment()).commit()
            }
        } else {
            binding.noPlantsTextView.visibility = View.GONE
            binding.noPlantAddButton.visibility = View.GONE
        }

    }

    fun deleteNotification(currentplant : MyPlant) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Radera växt")
        builder.setMessage("Vill du verkligen radera denna fina växt?")

        builder.setPositiveButton("Radera") { dialog, which ->
            model.deleteMyPlant(currentplant)
            model.createMyPlantList()
            model.homeFragment.showDeleteButton = false
            myPlantsAdapter.notifyDataSetChanged()

        }
        builder.setNegativeButton(("Ångra")) { dialog, which ->


        }

        builder.show()
    }



    fun goPlantDetails(chosenPlant : MyPlant) {
        val plantdetailsuneditablefrag = PlantDetailsUnEditableFragment()
        plantdetailsuneditablefrag.currentPlant = chosenPlant

        requireActivity().supportFragmentManager.beginTransaction().
        add(R.id.fragContainer, plantdetailsuneditablefrag).addToBackStack(null).commit()    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}