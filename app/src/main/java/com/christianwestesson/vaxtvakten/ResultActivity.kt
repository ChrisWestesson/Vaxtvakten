package com.christianwestesson.vaxtvakten

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_result

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //val txtTitleView = findViewById<TextView>(R.id.txtTitleView)
        //val txtMsgView = findViewById<TextView>(R.id.txtMsgView)

        if (intent.getBooleanExtra("notification", false)) { //Just for confirmation
            //txtTitleView.text = intent.getStringExtra("title")
           // txtMsgView.text = intent.getStringExtra("message")

        }


    }
}