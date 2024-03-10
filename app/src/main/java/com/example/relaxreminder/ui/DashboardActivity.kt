package com.example.relaxreminder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.relaxreminder.R
import com.example.relaxreminder.databinding.ActivityDashboardBinding
import com.example.relaxreminder.ui.fragments.CreateRoutineFragment
import com.example.relaxreminder.ui.fragments.HomeFragment
import com.example.relaxreminder.ui.fragments.ListarActividadesFragment
import com.example.relaxreminder.ui.interfaces.FragmentChangeListener
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FragmentChangeListener {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null) {
            loadDefaultFragment()
        }
    }

    private fun loadDefaultFragment() {
        val item: MenuItem =  navigationView.menu.getItem(0)
        onNavigationItemSelected(item);
    }

    private fun changeFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        when(item.itemId) {
            R.id.nav_item_home -> changeFragment(HomeFragment.newInstance(), item.title.toString())
            R.id.nav_item_routine -> changeFragment(ListarActividadesFragment.newInstance(), item.title.toString())
            }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentChange(fragment: Fragment) {
        val itemId = when(fragment) {
            is HomeFragment -> R.id.nav_item_home
            is CreateRoutineFragment -> R.id.nav_item_routine
            else -> return
        }
        val menuItem = navigationView.menu.findItem(itemId)
        onNavigationItemSelected(menuItem)
    }
}