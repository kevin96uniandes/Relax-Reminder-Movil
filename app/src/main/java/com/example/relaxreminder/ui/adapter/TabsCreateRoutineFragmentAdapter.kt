package com.example.relaxreminder.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.relaxreminder.ui.fragments.ListAlarmsFragment
import com.example.relaxreminder.ui.fragments.ListProgrammingsFragment

class TabsCreateRoutineFragmentAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val tabTitles: List<String>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        // Retorna el Fragment correspondiente a cada pestaña
        // Aquí debes crear y retornar el Fragment para cada posición
        return when(position) {
            0 -> ListAlarmsFragment()
            1 -> ListProgrammingsFragment()
            else -> throw IllegalArgumentException("Posición inválida: $position")
        }
    }
}