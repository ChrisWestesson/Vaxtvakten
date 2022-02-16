package com.christianwestesson.vaxtvakten

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.christianwestesson.vaxtvakten.databinding.FragmentAddUnlistedPlanBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding


class AddUnlistedPlanFragment : Fragment() {

    var _binding: FragmentAddUnlistedPlanBinding? = null
    val binding get () = _binding!!

    val REQUEST_IMAGE_CAPTURE = 1

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

        binding.btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.nextToAddListBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragContainer, AddListedPlantFragment()).addToBackStack(null).commit()
        }
    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imgViewer.setImageBitmap(imageBitmap)
        }
    }
}
