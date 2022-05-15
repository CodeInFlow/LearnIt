package com.codinginflow.learnit

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.codinginflow.learnit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val toolbar = binding.toolbar
        val toolbarLogo: ImageView = binding.toolbarLogo
        val expandedToolbarBox: LinearLayout = binding.expandedToolbarBox
        val expandedToolbarTitle: TextView = binding.expandedToolbarTitle
        val expandedToolbarSubtitle: TextView = binding.expandedToolbarSubtitle
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        val bottomNavActiveFrag = listOf(
            R.id.nav_home,
            R.id.nav_search,
            R.id.nav_learning
        )

        val expandedToolbar = listOf(
            R.id.nav_notifications,
            R.id.nav_account
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (bottomNavActiveFrag.contains(destination.id)) {
                navView.visibility = View.VISIBLE
                toolbarLogo.visibility = View.VISIBLE
                toolbar.navigationIcon = null
                supportActionBar?.title = ""
            } else {
                navView.visibility = View.GONE
                toolbarLogo.visibility = View.GONE
            }
            expandedToolbarBox.visibility = if (expandedToolbar.contains(destination.id)) VISIBLE else GONE
            when (destination.id) {
                R.id.nav_notifications -> {
                    expandedToolbarTitle.text = "Nav Notifications"
                    expandedToolbarSubtitle.text = "Hello world"
                }
                R.id.nav_account -> {
                    expandedToolbarTitle.text = "My Account"
                    expandedToolbarSubtitle.text = "Hello earth"
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}