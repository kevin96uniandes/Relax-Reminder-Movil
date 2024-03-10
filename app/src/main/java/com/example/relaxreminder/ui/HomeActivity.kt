package com.example.relaxreminder.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.relaxreminder.R
import com.example.relaxreminder.adapter.ActivityItemAdapter
import com.example.relaxreminder.adapter.AlarmaItemAdapter
import com.example.relaxreminder.models.Activity
import com.example.relaxreminder.models.Alarma

class HomeActivity: AppCompatActivity(){

    private var adapter: AlarmaItemAdapter? = null
    val data: ArrayList<Alarma> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        for (i in 0..3) {
            val alarma = Alarma("Alarma " + i, "9:00 am Viernes, Mar 01")
            data.add(alarma)
        }

        var recyclerView: RecyclerView = findViewById(R.id.recycleViewAlarmas)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = AlarmaItemAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.VISIBLE
    }
}