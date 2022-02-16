package com.christianwestesson.vaxtvakten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.christianwestesson.vaxtvakten.databinding.FragmentAddUnlistedPlanBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding


class AddUnlistedPlanFragment : Fragment() {

    var _binding: FragmentAddUnlistedPlanBinding? = null
    val binding get () = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddUnlistedPlanBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.namnET.text

        binding.btnTakePicture.setOnClickListener {
            // Camera code
        }

        binding.nextToAddListBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().add(R.id.addListFrag, AddListedPlantFragment()).addToBackStack(null).commit()
        }
    }
}