package com.example.relaxreminder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.relaxreminder.R
import com.example.relaxreminder.models.Activity
import com.example.relaxreminder.models.Alarma

class AlarmaItemAdapter(private val alarmas: List<Alarma>): RecyclerView.Adapter<AlarmaItemAdapter.AlarmaViewHolder>() {

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
        holder.descripcionAlarma.text = item.descripcion
    }

    inner class AlarmaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreAlarma: TextView = itemView.findViewById(R.id.nombre_alarma)
        val descripcionAlarma: TextView = itemView.findViewById(R.id.detalle_alarma)

    }
}