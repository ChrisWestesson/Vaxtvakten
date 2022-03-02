package com.christianwestesson.vaxtvakten

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import com.christianwestesson.vaxtvakten.databinding.FragmentPlantDetailsBinding


class PlantDetailsFragment : Fragment() {

    var _binding: FragmentPlantDetailsBinding? = null
    val binding get () = _binding!!

    val model : MyPlantViewModel by activityViewModels()

    var waterinterval = 0
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_plant_details, container, false)
        _binding = FragmentPlantDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.addPlant()

        var templist = model.myplant.value!![0]

        binding.editTextTextPersonName.setText(templist.title)
        binding.editTextTextPersonName2.setText(templist.species)
        binding.editTextTextPersonName3.setText(templist.wateramount)
        binding.editTextTextPersonName4.setText(templist.waterinterval.toString())
        binding.editTextTextPersonName5.setText(templist.info)

        binding.button5.setOnClickListener {
                counter = templist.waterinterval!!
                startTimer()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun startTimer() {

        var fulltimer = 100
        var countertimesby : Double = fulltimer.toDouble() / counter.toDouble()
        var counterMinutes = 0
        var counterHours = 0
        var counterDays = 0
        var counterpercent : Double = 0.0


       // val countTime: TextView = findViewById(R.id.timerTV)
        val countTimeDays = binding.textView28
        val countTimeHours = binding.textView29
        val countTimeMinutes = binding.textView30
        val progressbar = binding.timeLeftProgressBar
       // val counterTimesbyTV : TextView = findViewById<TextView>(R.id.countertimesbyTV)
        object : CountDownTimer(counter.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

               // countTime.text = counter.toString()
                counterDays = counter / 86400
                counterHours = counter / 3600
                counterMinutes = counter / 60
                counterMinutes = counterMinutes - counterHours * 60
                counterHours = counterHours - counterDays * 24

                countTimeHours.text = counterHours.toString()
                countTimeDays.text = counterDays.toString()
                countTimeMinutes.text = counterMinutes.toString()


                counterpercent = counter.toDouble() * countertimesby
                //findViewById<ProgressBar>(R.id.progressBar).setProgress(counter, true)
                progressbar.setProgress(counterpercent.toInt(), true)
                counter--
                //counterTimesbyTV.text = counterpercent.toString()
            }
            override fun onFinish() {
                progressbar.setProgress(0, true)


            }
        }.start()
    }


}