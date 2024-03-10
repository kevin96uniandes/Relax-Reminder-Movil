package com.example.relaxreminder.ui.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.relaxreminder.R
import com.example.relaxreminder.model.Alarm

class AlarmsAdapter(private val alarms: List<Alarm>, private val context: Context) :
        RecyclerView.Adapter<AlarmsAdapter.AlarmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item_fragment, parent, false)
        return AlarmViewHolder(view)
    }

    override fun getItemCount(): Int = alarms.size

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = alarms[position]
        holder.bind(alarm)
    }

    inner class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(alarm: Alarm) {
            itemView.findViewById<TextView>(R.id.tv_alarm_name).text = alarm.name
            itemView.findViewById<TextView>(R.id.tv_start_in).text = "${alarm.start} Minutos"
            itemView.findViewById<TextView>(R.id.tv_end_in).text = "${alarm.end} Minutos"


            val btnOptions = itemView.findViewById<ImageButton>(R.id.btn_options)
            val popupMenu = PopupMenu(context, btnOptions)
            popupMenu.inflate(R.menu.alarm_item_menu)

            btnOptions.setOnClickListener {
                popupMenu.show()
            }

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_edit -> {
                        // Acción para editar
                        true
                    }
                    R.id.action_delete -> {
                        // Acción para eliminar
                        true
                    }
                    else -> false
                }
            }
        }
    }
}