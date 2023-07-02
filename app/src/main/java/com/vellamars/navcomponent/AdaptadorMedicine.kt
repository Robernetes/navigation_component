package com.vellamars.navcomponent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorMedicine(
    val listMedicine: MutableList<Medicine>,
    var listener : AdaptadorListener

): RecyclerView.Adapter<AdaptadorMedicine.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicine = listMedicine[position]
        holder.textViewMedicine.text = medicine.medicineItem
        //holder.textViewDate.text = medicine.medicineDate

        holder.btnDelete.setOnClickListener {
            listener.onDeleteItemClick(medicine)
        }
    }

    override fun getItemCount(): Int {
        return listMedicine.size
    }


    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cardView = itemView.findViewById<CardView>(R.id.cardViewMedicine)
        val textViewMedicine = itemView.findViewById<TextView>(R.id.cardTextViewMedicine)
       // val textViewDate = itemView.findViewById<TextView>(R.id.cardTextViewDate)
        val btnDelete = itemView.findViewById<Button>(R.id.cardButtonDelete)

    }

}