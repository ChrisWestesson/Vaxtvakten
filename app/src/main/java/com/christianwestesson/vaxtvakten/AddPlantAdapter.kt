package com.christianwestesson.vaxtvakten

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddPlantAdapter() : RecyclerView.Adapter<AddPlantViewHolder>() {

    lateinit var  addplantfrag : AddPlantListFragment

    var flowers = mutableListOf<String>("Amaryllis", "Aralia", "Aspidistra", "Begonia", "Benjaminfikus",
        "Cyklamen", "Flitiga Lisa", "Fredskalla", "Gloxinia", "Hemtrevnad", "Julstjärna", "Monstera", "Paradisträd",
        "Pelargon", "Prickblad", "Silverkalla", "Spjutbräken", "Svärmors kudde", "Svärmors tunga", "Skvallerreva",
        "Våreld")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlantViewHolder {
        val vh = AddPlantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recview_add_plant, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return flowers.size
    }

    override fun onBindViewHolder(holder: AddPlantViewHolder, position: Int) {


        holder.flowertext.text = flowers[position]

        holder.itemView.setOnClickListener {
            addplantfrag.goChoosenPlant()
        }


    }

}

class AddPlantViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var flowertext = view.findViewById<TextView>(R.id.addplantTV)




}