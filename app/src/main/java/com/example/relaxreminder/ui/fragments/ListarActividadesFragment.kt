package com.example.relaxreminder.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.relaxreminder.R
import com.example.relaxreminder.model.Activity
import com.example.relaxreminder.ui.adapter.ActivityItemAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListarActividadesFragment : Fragment() {
    private var adapter: ActivityItemAdapter? = null
    val data: ArrayList<Activity> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireNotNull(this.activity)
        activity.title = "Lista de rutinas"
        val view = inflater.inflate(R.layout.activity_lista_actividades, container, false)

        for (i in 0..3) {
            val actividad = Activity("Mi rutina favorita " + i, "Esta es mi rutina favorita")
            data.add(actividad)
        }

        setArreglo(data, view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchView = view.findViewById<SearchView>(R.id.search_routine)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val filteredList = data.filter { it.name.contains(newText, ignoreCase = true) }
                setArreglo(filteredList, view)
                return true
            }
        })

        val createRoutineButton = view.findViewById<FloatingActionButton>(R.id.add_routine)

        createRoutineButton.setOnClickListener {
            navigateToCreateRoutine()
        }

    }

    private fun setArreglo(data: List<Activity>, view: View) {

        var recyclerView: RecyclerView = view.findViewById(R.id.recycleViewActividades)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ActivityItemAdapter(data, this)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListarActividadesFragment()
    }

    private fun navigateToCreateRoutine(){
        val transaction = this.activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_layout, CreateRoutineFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    fun showWarningDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_warning, null)
        builder.setView(dialogView)

        val animationView = dialogView.findViewById<LottieAnimationView>(R.id.animation_view)
        animationView.setAnimation(R.raw.warning)

        animationView.playAnimation()
        val tvMsgSuccess = dialogView.findViewById<TextView>(R.id.tv_msg_dialog_success)
        val routineStr = ""
        val successfulStr = "Una vez se quite no podrá recuperarla"
        tvMsgSuccess.text = "$routineStr $successfulStr"
        val dialog: AlertDialog = builder.create()
        val btnSi = dialogView.findViewById<MaterialButton>(R.id.btn_si)
        val btnNo = dialogView.findViewById<MaterialButton>(R.id.btn_no)
        btnSi.setOnClickListener {
            dialog.dismiss()
        }
        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}