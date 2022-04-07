package com.christianwestesson.vaxtvakten

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import com.christianwestesson.vaxtvakten.databinding.FragmentAddUnlistedPlanBinding
import com.christianwestesson.vaxtvakten.databinding.FragmentPlantDetailsBinding


class PlantDetailsFragment : Fragment() ,AdapterView.OnItemSelectedListener {

    var _binding: FragmentPlantDetailsBinding? = null
    val binding get () = _binding!!

    val model : MyPlantViewModel by activityViewModels()
    var myPlantsAdapter = MyPlantsAdapter()

    var waterinterval = 0
    var counter = 0

    var currentPlant = MyPlant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 0,
        waterintervalHours = 0, info = "", species = "", title = "",
        wateramount = "", giveWaterDate = 0, imgName = "", userimgName = "")

    var selectWeeks = IntArray(13){it}
    var selectDays = IntArray(7){it}
    var selectHours = IntArray(24){it}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPlantDetailsBinding.inflate(inflater, container, false)
        inflater.inflate(R.layout.fragment_plant_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


        //model.addPlant()

//        var templist = model.myplant.value!![0]

        //binding.editTextName.text = currentPlant.title

        binding.editTextName.setText(currentPlant.title)
        binding.editTextSpecies.setText(currentPlant.species).toString()
        binding.editTextWateramount.setText(currentPlant.wateramount)
        //binding.editTextTextPersonName4.setText(templist.waterinterval.toString())
        //binding.dagarSpinner.onItemSelectedListener = this
        binding.editTextInfo.setText(currentPlant.info)


        binding.veckorSpinner.setSelection(currentPlant.waterintervalWeeks, true)
        binding.dagarSpinner.setSelection(currentPlant.waterintervalDays, true)
        binding.timmerSpinner.setSelection(currentPlant.waterintervalHours, true)


        //startTimer()

        binding.saveButton.setOnClickListener {
            vibrateOnClick()

            currentPlant.title = binding.editTextName.text.toString()
            currentPlant.species = binding.editTextSpecies.text.toString()
            currentPlant.wateramount = binding.editTextWateramount.text.toString()
            currentPlant.info = binding.editTextInfo.text.toString()

            Log.i("PIAXDEBUG", "currentplant weeks: " + currentPlant.waterintervalWeeks + " currenplantname: " +  currentPlant.title + " name: " +  binding.editTextName.text.toString())
            Log.i("PIAXDEBUG", "currentplant days: " + currentPlant.waterintervalDays)
            Log.i("PIAXDEBUG", "currentplant hours: " + currentPlant.waterintervalHours)

            var interval = currentPlant.waterintervalWeeks + currentPlant.waterintervalDays + currentPlant.waterintervalHours

            if (interval == 0) {
                intervalNotProvidedNotification()
            } else {
                model.updateMyPlant(currentPlant)
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragContainer, HomeFragment()).commit()

            }


        }

        binding.dagarSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("PIAXDEBUG", "SELECT " + position.toString() + "Days: " +  selectDays[position])
                currentPlant.waterintervalDays = selectDays[position]
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.parseColor("#FF000000"))
            }
        }
        binding.veckorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("PIAXDEBUG", "SELECT " + position.toString() + "Weeks: " +  selectWeeks[position])
                currentPlant.waterintervalWeeks = selectWeeks[position]
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.parseColor("#FF000000"))
            }
        }
        binding.timmerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("PIAXDEBUG", "SELECT " + position.toString() + "Hours: " +  selectHours[position])
                currentPlant.waterintervalHours = selectHours[position]
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.parseColor("#FF000000"))
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*

    fun startTimer() {

        var fulltimer = 100
        var countertimesby : Double = fulltimer.toDouble() / counter.toDouble()
        var counterMinutes = 0
        var counterHours = 0
        var counterDays = 0
        var counterpercent : Double = 0.0


       // val countTime: TextView = findViewById(R.id.timerTV)
        //val countTimeDays = binding.textView28
        //val countTimeHours = binding.textView29
        //val countTimeMinutes = binding.textView30
        //val progressbar = binding.timeLeftProgressBar
       // val counterTimesbyTV : TextView = findViewById<TextView>(R.id.countertimesbyTV)
        object : CountDownTimer(counter.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

               // countTime.text = counter.toString()
                counterDays = counter / 86400
                counterHours = counter / 3600
                counterMinutes = counter / 60
                counterMinutes = counterMinutes - counterHours * 60
                counterHours = counterHours - counterDays * 24

                //countTimeHours.text = counterHours.toString()
                //countTimeDays.text = counterDays.toString()
                //countTimeMinutes.text = counterMinutes.toString()


                counterpercent = counter.toDouble() * countertimesby
                //findViewById<ProgressBar>(R.id.progressBar).setProgress(counter, true)
                //progressbar.setProgress(counterpercent.toInt(), true)
                counter--
                //counterTimesbyTV.text = counterpercent.toString()
            }
            override fun onFinish() {
                //progressbar.setProgress(0, true)
            }
        }.start()
    }

    */
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        val dagarSp = "you selected choice # " + position
        val timerSp = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this.context,dagarSp,timerSp)
        toast.show()
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    fun vibrateOnClick() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(50)
        }
    }
    fun intervalNotProvidedNotification() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Frekvens saknas!")
        builder.setMessage("Hur ofta ska växten vattnas? Ange frekvens för att kunna spara ändringar.")

        builder.setPositiveButton("Okej") { dialog, which ->
        }

        builder.show()
    }
}
