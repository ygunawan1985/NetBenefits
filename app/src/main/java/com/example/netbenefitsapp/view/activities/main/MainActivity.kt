package com.example.netbenefitsapp.view.activities.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.view.fragments.LogoutFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private val fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var logoutFragment: LogoutFragment
    private lateinit var textMessage: TextView
    private lateinit var mUser : FirebaseUser


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_planning -> {
                textMessage.setText(R.string.title_planning)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_learn -> {
                textMessage.setText(R.string.title_learn)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        mUser = FirebaseAuth.getInstance().currentUser!!

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        setupAndAddLogoutFragment()
    }

    private fun setupAndAddLogoutFragment() {
        logoutFragment = LogoutFragment()
        fragmentManager.beginTransaction().add(R.id.logoutContainer, logoutFragment).addToBackStack("LOGOUT_FRAGMENT").commit()
    }
}
