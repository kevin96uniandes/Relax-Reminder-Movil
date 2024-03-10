package com.example.relaxreminder.ui

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.relaxreminder.R
import com.example.relaxreminder.adapter.ActivityItemAdapter
import com.example.relaxreminder.models.Activity

class ListarActividadesActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView? = null
    private var adapter: ActivityItemAdapter? = null
    val data: ArrayList<Activity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_actividades)
        for (i in 0..3) {
            val actividad = Activity("Mi rutina favorita " + i, "Esta es mi rutina favorita")
            data.add(actividad)
        }

        setArreglo(data)
        setupSearchView()
    }

    private fun setArreglo(data: List<Activity>) {
        var recyclerView: RecyclerView = findViewById(R.id.recycleViewActividades)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ActivityItemAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.VISIBLE
    }

    private fun setupSearchView() {
        val searchView = findViewById<SearchView>(R.id.search_routine)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val filteredList = data.filter { it.name.contains(newText, ignoreCase = true) }
                setArreglo(filteredList)
                return true
            }
        })


    }
}