package com.example.relaxreminder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.relaxreminder.R
import com.example.relaxreminder.databinding.FragmentCreateRoutineBinding
import com.example.relaxreminder.ui.adapter.TabsCreateRoutineFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Locale

class CreateRoutineFragment : Fragment() {
    private lateinit var binding: FragmentCreateRoutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateRoutineBinding.inflate(inflater, container, false)

// Crear un adaptador para el ViewPager
        val adapter = TabsCreateRoutineFragmentAdapter(requireActivity().supportFragmentManager, lifecycle, listOf("Alarmas", "Programaciones"))
        binding.viewPager.adapter = adapter

// Vincular el TabLayout con el ViewPager
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Aquí puedes establecer el texto de las pestañas
            tab.text = adapter.tabTitles[position].lowercase(Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }.attach()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateRoutineFragment()
    }
}