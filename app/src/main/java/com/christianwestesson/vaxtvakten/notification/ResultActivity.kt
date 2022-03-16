package com.christianwestesson.vaxtvakten.notification




import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.christianwestesson.vaxtvakten.R

//import kotlinx.android.synthetic.main.activity_result

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_result)



        if (intent.getBooleanExtra("notification", false)) { //Just for confirmation
           // txtTitleView.text = intent.getStringExtra("title")
            //txtMsgView.text = intent.getStringExtra("message")

        }


    }
}