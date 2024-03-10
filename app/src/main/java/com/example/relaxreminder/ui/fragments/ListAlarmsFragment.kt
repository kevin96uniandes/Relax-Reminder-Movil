package com.example.relaxreminder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.relaxreminder.R
import com.example.relaxreminder.databinding.FragmentListAlarmsBinding
import com.example.relaxreminder.model.Alarm
import com.example.relaxreminder.ui.adapter.AlarmsAdapter

class ListAlarmsFragment : Fragment() {

    private var alarms = listOf(
        Alarm(
            name = "Caminar",
            start = "50",
            end = "20",
            id = "1"
        )
    )


    private lateinit var binding: FragmentListAlarmsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListAlarmsBinding.inflate(inflater, container, false)

        if (alarms.isNotEmpty()) {
            binding.tvMsgBlankAlarms.visibility = View.GONE
        }

        val recyclerView: RecyclerView = binding.recyclerViewAlarms
        val alarmAdapter = AlarmsAdapter(this.alarms, requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = alarmAdapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListAlarmsFragment()
    }
}