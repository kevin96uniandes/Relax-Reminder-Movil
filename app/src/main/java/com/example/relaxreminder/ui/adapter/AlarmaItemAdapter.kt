package com.example.relaxreminder.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.relaxreminder.R
import com.example.relaxreminder.model.Alarm
import com.google.android.material.button.MaterialButton

class AlarmaItemAdapter(private val alarmas: List<Alarm>): RecyclerView.Adapter<AlarmaItemAdapter.AlarmaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alarma_item, parent, false)
        return AlarmaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return alarmas.size
    }

    override fun onBindViewHolder(holder: AlarmaViewHolder, position: Int) {
        val item = alarmas[position]
        holder.nombreAlarma.text = item.name
        holder.descripcionAlarma.text = "9:00 am Viernes, Mar 01"
    }

    inner class AlarmaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreAlarma: TextView = itemView.findViewById(R.id.nombre_alarma)
        val descripcionAlarma: TextView = itemView.findViewById(R.id.detalle_alarma)

    }

}