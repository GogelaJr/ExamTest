package com.example.examtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.examtest.fragments.FavouritesFragment
import com.example.examtest.fragments.HomepageFragment
import com.example.examtest.fragments.LogOutFragment
import com.example.examtest.fragments.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val log = LoginFragment()
//        val home = HomepageFragment()
//        val favourite = FavouritesFragment()
//        val logout = LogOutFragment()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBar = AppBarConfiguration(setOf(
            R.id.homepageFragment,
            R.id.favouritesFragment,
            R.id.logOutFragment
        ))

        setupActionBarWithNavController(navController, appBar)
        navView.setupWithNavController(navController)

//        makeCurrentFragment(home)
//
//        val bottom_nav:BottomNavigationView = findViewById(R.id.nav_view)
//
//        bottom_nav.setOnNavigationItemSelectedListener {
//            when (it.itemId){
//                R.id.homeIconFragment -> makeCurrentFragment(home)
//                R.id.favoritesIconFragment -> makeCurrentFragment(favourite)
//                R.id.logoutIconFragment -> makeCurrentFragment(logout)
//
//            }
//            true
//        }
    }

//    private fun makeCurrentFragment(fragment: Fragment) =
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.nav_host_fragment, fragment)
//            commit()
//        }

}