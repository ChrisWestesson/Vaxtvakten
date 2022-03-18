package com.christianwestesson.vaxtvakten

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.christianwestesson.vaxtvakten.Databasehelper.Companion.model
import com.christianwestesson.vaxtvakten.databinding.FragmentAddUnlistedPlanBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*


class AddUnlistedPlanFragment : Fragment() {

    var _binding: FragmentAddUnlistedPlanBinding? = null
    val binding get () = _binding!!

    val REQUEST_IMAGE_CAPTURE = 1

    var imagename = ""

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

        var date = Calendar.getInstance().timeInMillis

        binding.namnET.text

        binding.btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.nextToAddListBtn.setOnClickListener {

            var addToMyPlantList = MyPlant(
                uid = 0,
                waterintervalWeeks = 1,
                waterintervalDays = 0,
                waterintervalHours = 0,
                info = binding.infoET.text.toString(),
                species = binding.artET.text.toString(),
                title = binding.namnET.text.toString(),
                wateramount = binding.vattenmNgdET.text.toString(),
                giveWaterDate = date,
                imgName = "",
                userimgName = imagename)

            var addToPlantList = Plant(
                uid = 0,
                waterintervalWeeks = 1,
                waterintervalDays = 0,
                waterintervalHours = 0,
                info = binding.infoET.text.toString(),
                species = binding.artET.text.toString(),
                title = "",
                wateramount = binding.vattenmNgdET.text.toString(),
                giveWaterDate = date,
                imgName = "",
                userimgName = imagename)

            model.addMyPlant(addToMyPlantList)
            model.addPlant(addToPlantList)



            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragContainer, HomeFragment()).commit()
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

            val imageuuid = UUID.randomUUID().toString()

            val stream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            val imagedata = stream.toByteArray()

            val imagedir = File(requireContext().getExternalFilesDir("vaxtvakten"), "plantimages")
            imagedir.mkdirs()
            val imagefile = File(imagedir, imageuuid+".jpg")
            imagename = imageuuid+".jpg"

            val fileout = FileOutputStream(imagefile)
            fileout.write(imagedata)


            binding.imgViewer.setImageBitmap(imageBitmap)
        }
    }

    /*
    fun getimage()
    {
        val theuuid = "xyz"

        val imagedir = File(requireContext().getExternalFilesDir(), "plantimages")
        val imagefile = File(imagedir, theuuid+".jpg")

        val imagebytes = imagefile.readBytes()
        val bitmap = BitmapFactory.decodeByteArray(imagebytes)
    }

     */

}
