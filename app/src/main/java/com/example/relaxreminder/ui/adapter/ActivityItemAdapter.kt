package com.example.relaxreminder.ui.adapter


import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.relaxreminder.R
import com.example.relaxreminder.model.Activity
import com.example.relaxreminder.ui.fragments.ListarActividadesFragment
import com.google.android.material.button.MaterialButton

class ActivityItemAdapter(private val actividades: List<Activity>, private val listaFragment: ListarActividadesFragment): RecyclerView.Adapter<ActivityItemAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actividad_lista_item, parent, false)
        return ActivityViewHolder(view)
    }

    override fun getItemCount(): Int {
       return actividades.size

    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val item = actividades[position]
        holder.textView.text = item.name

        holder.deleteButton.setOnClickListener {
            listaFragment.showWarningDialog()
        }

    }

    private fun showActions(holder: ActivityViewHolder) {
        //holder.editButton.visibility = View.VISIBLE
        holder.deleteButton.visibility = View.VISIBLE
    }

    private fun hideActions(holder: ActivityViewHolder) {
        //holder.editButton.visibility = View.GONE
        holder.deleteButton.visibility = View.GONE

    }


    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView: TextView = itemView.findViewById(R.id.contenidoActividad)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

    }




}
