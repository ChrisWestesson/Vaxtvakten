package com.christianwestesson.vaxtvakten

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.christianwestesson.vaxtvakten.Databasehelper.Companion.model
import com.christianwestesson.vaxtvakten.databinding.FragmentAddUnlistedPlanBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*


class AddUnlistedPlanFragment : Fragment() {

    var _binding: FragmentAddUnlistedPlanBinding? = null
    val binding get () = _binding!!

    val REQUEST_IMAGE_CAPTURE = 1

    var imagename = ""

    var selectWeeks = IntArray(13){it}
    var selectDays = IntArray(7){it}
    var selectHours = IntArray(24){it}

    var myPlant = MyPlant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 0,
        waterintervalHours = 0, info = "", species = "", title = "",
        wateramount = "", giveWaterDate = 0, imgName = "", userimgName = "")

    var newPlant = Plant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 0,
        waterintervalHours = 0, info = "", species = "", title = "",
        wateramount = "", giveWaterDate = 0, imgName = "", userimgName = "")

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


        binding.btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.nextToAddListBtn.setOnClickListener {


            myPlant.title = binding.nameET.text.toString()
            myPlant.species = binding.speciesET.text.toString()
            myPlant.wateramount = binding.wateramountET.text.toString()
            myPlant.info = binding.infoET.text.toString()
            myPlant.giveWaterDate = date
            myPlant.userimgName = imagename



            newPlant.species = binding.speciesET.text.toString()
            newPlant.wateramount = binding.wateramountET.text.toString()
            newPlant.info = binding.infoET.text.toString()
            newPlant.giveWaterDate = date
            newPlant.userimgName = imagename

            var interval = myPlant.waterintervalWeeks + myPlant.waterintervalDays + myPlant.waterintervalHours

            if (interval == 0) {
                intervalNotProvidedNotification()
            } else {
                model.addMyPlant(myPlant)
                model.addPlant(newPlant)
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragContainer, HomeFragment()).commit()
                Snackbar.make(view, ("Din växt är tillagd!"), Snackbar.LENGTH_SHORT).show()

            }

        }

        val spinner : Spinner = binding.dagarSpinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.Dagar,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        val spinner1 : Spinner = binding.veckorSpinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.Veckor,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner1.adapter = adapter
        }
        val spinner2 : Spinner = binding.timmerSpinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.Timmer,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }

        binding.dagarSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("PIAXDEBUG", "SELECT " + position.toString())
                myPlant.waterintervalDays = selectDays[position]
                newPlant.waterintervalDays = selectDays[position]
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.parseColor("#FF000000"))
            }
        }
        binding.timmerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("PIAXDEBUG", "SELECT " + position.toString())
                myPlant.waterintervalHours = selectHours[position]
                newPlant.waterintervalHours = selectHours[position]
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.parseColor("#FF000000"))
            }
        }
        binding.veckorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("PIAXDEBUG", "SELECT " + position.toString())
                myPlant.waterintervalWeeks = selectWeeks[position]
                newPlant.waterintervalWeeks = selectWeeks[position]
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.parseColor("#FF000000"))
            }
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

    fun intervalNotProvidedNotification() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Frekvens saknas!")
        builder.setMessage("Du måste ange hur ofta växten ska vattnas för att kunna lägga till den.")

        builder.setPositiveButton("Okej") { dialog, which ->
        }

        builder.show()
    }



}
