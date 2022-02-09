package com.christianwestesson.vaxtvakten

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyPlantsAdapter() : RecyclerView.Adapter<AddPlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlantViewHolder {
        val vh = AddPlantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recview_my_plants, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: AddPlantViewHolder, position: Int) {

    }

}

class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {



}