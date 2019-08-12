package com.example.netbenefitsapp.view.activities.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity
import com.example.netbenefitsapp.view.fragments.*
import com.example.netbenefitsapp.viewmodel.main.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity(), MarketFragment.OnFragmentInteractionListener {

    private val fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var logoutFragment: LogoutFragment
    private lateinit var mUser : FirebaseUser
    private lateinit var mainActivityViewModel : MainActivityViewModel
    private var stockResponseList : List<StockResponse> = ArrayList()
    lateinit var toolbar : ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUser = FirebaseAuth.getInstance().currentUser!!
        toolbar = supportActionBar!!
        toolbar.title = "Welcome, " + mUser.displayName

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        //Bottom navigation bar
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //ViewModel binding
        mainActivityViewModel = MainActivityViewModel()

        //set up the initial home fragment
        val homeFragment = HomeFragment.newInstance()
        openFragments(homeFragment)

        //set up logout fragment
        setupAndAddLogoutFragment()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                toolbar.title = mUser.email
                val homeFragment = HomeFragment.newInstance()
                openFragments(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_planning -> {
                toolbar.title = "Planning"
                val planningFragment = PlanningFragment()
                openFragments(planningFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_learn -> {
                toolbar.title = "Learn"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_market -> {
                toolbar.title = "Market"
                val marketFragment = MarketFragment.newInstance()
                openFragments(marketFragment)
                val resultFragment = ResultFragment.newInstance(ArrayList(stockResponseList))
                resultFragment.setStocks(stockResponseList)
                openFragments(resultFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_actions -> {
                toolbar.title = "Actions"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onFragmentInteraction(stockResponseList: List<StockResponse>) {
        this.stockResponseList = stockResponseList
    }

    private fun setupAndAddLogoutFragment() {
        logoutFragment = LogoutFragment()
        fragmentManager.beginTransaction().add(R.id.logoutContainer, logoutFragment).addToBackStack("LOGOUT_FRAGMENT").commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.profile -> {
                mainActivityViewModel.displayProfile(this)
            }
            R.id.contactUs -> {
                val contactUsIntent = Intent(Intent.ACTION_VIEW)
                contactUsIntent.setData(Uri.parse("https://www.fidelity.com/customer-service/contact-us"))
                startActivity(contactUsIntent)
            }
            R.id.feedback -> {
                val feedbackIntent = Intent(Intent.ACTION_VIEW)
                feedbackIntent.setData(Uri.parse("https://www.fidelity.com/why-fidelity/ratings-reviews"))
                startActivity(feedbackIntent)
            }
            R.id.importantLegalInformation -> {
                val legalInformationIntent = Intent(Intent.ACTION_VIEW)
                legalInformationIntent.setData(Uri.parse("https://www.fidelity.com/psw/WS_PSW_ILI/0,,,00.html"))
                startActivity(legalInformationIntent)
            }
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, WelcomeActivity::class.java))
            }
        }

        return true
    }

    private fun openFragments(fragment : Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
