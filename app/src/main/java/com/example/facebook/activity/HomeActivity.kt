package com.example.facebook.activity

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.facebook.R
import com.example.facebook.databinding.ActivityHomeBinding
import com.example.facebook.fragment.HomeMainFragmentDirections
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.HomeActivityViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    private val navHostFragment: NavHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment
    private val navController: NavController
        get() = navHostFragment.navController

    override fun setupViews() {
        dataBinding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
        })
        dataBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_nav_btn -> {
                    Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()

                }
                R.id.friends_nav_btn -> {
                    Toast.makeText(this, "friends", Toast.LENGTH_SHORT).show()
                }
                R.id.profile_nav_btn -> {
                    val action =
                        HomeMainFragmentDirections.actionHomeMainFragmentToProfilePageFragment()
                    this.navigate(action)
                }
                R.id.create_post_nav_btn -> {
                    startActivity(Intent(this, CreatePostActivity::class.java))
                }
            }
            true
        }

    }

    fun Activity.navigate(directions: NavDirections) {
        val currentDestination =
            (navController.currentDestination as? FragmentNavigator.Destination)?.className
                ?: (navController.currentDestination as? DialogFragmentNavigator.Destination)?.className
        if (currentDestination == this.javaClass.name) {
            Log.e("TAG", "navigate: $currentDestination")
            navController.navigate(directions)
        }
    }

    override fun getViewModel() = HomeActivityViewModel::class.java


    override fun getResourceId(): Int = R.layout.activity_home

}