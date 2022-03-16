package com.christianwestesson.vaxtvakten

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

        addplantfrag.model.plantList.value?.let {
            return it.size
        }

        return 0

    }

    override fun onBindViewHolder(holder: AddPlantViewHolder, position: Int) {

        var currentPlant = addplantfrag.model.plantList.value!![position]
        holder.flowerImage.setImageResource(addplantfrag.model.stringtoIMG(currentPlant.species))

        Log.i("VAXTVAKTENDEBUG", "currentPlant: ${currentPlant.toString()}")
        Log.i("VAXTVAKTENDEBUG", "currentPlant.species: ${currentPlant.species.toString()}")

        holder.flowertext.text = currentPlant.species

        holder.itemView.setOnClickListener {
            addplantfrag.goChoosenPlant(currentPlant)
        }


    }

}

class AddPlantViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var flowertext = view.findViewById<TextView>(R.id.addplantTV)
    var flowerImage = view.findViewById<ImageView>(R.id.imageViewAddPlant)




}