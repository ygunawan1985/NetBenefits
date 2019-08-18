package com.example.netbenefitsapp.view.activities.main

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.Article
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.model.Video
import com.example.netbenefitsapp.model.datasource.local.LibraryDatabase
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import com.example.netbenefitsapp.view.activities.loggedout.LoggedOutActivity
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity
import com.example.netbenefitsapp.view.fragments.*
import com.example.netbenefitsapp.viewmodel.main.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity(), MarketFragment.OnFragmentInteractionListener {

    private val fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var logoutFragment: LogoutFragment
    private var mFirebaseUser : FirebaseUser? = null
    private var mUser : User? = null
    private lateinit var mainActivityViewModel : MainActivityViewModel
    private var stockResponseList : List<StockResponse> = ArrayList()
    lateinit var toolbar : ActionBar
    private lateinit var articles : ArrayList<Article>
    private lateinit var videos : ArrayList<Video>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.color_green)))

        val bundle = intent?.getBundleExtra("bundle")
        mFirebaseUser = bundle?.getParcelable("firebaseUser")
        mUser = bundle?.getParcelable("user")

        toolbar = supportActionBar!!
        toolbar.title = mUser?.company

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

        val libraryDatabase = LibraryDatabase()
        libraryDatabase.populateArticles()
        articles = libraryDatabase.getArticles()
        libraryDatabase.populateVideos()
        videos = libraryDatabase.getVideos()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                toolbar.title = mUser?.company
                val homeFragment = HomeFragment.newInstance()
                openFragments(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_planning -> {
                toolbar.title = getString(R.string.planning)
                val planningFragment = PlanningFragment()
                openFragments(planningFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_learn -> {
                toolbar.title = getString(R.string.learn)
                val learningFragment = LearnFragment.newInstance(articles, videos)
                openFragments(learningFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_market -> {
                toolbar.title = getString(R.string.market)
                val marketFragment = MarketFragment.newInstance()
                openFragments(marketFragment)
                val resultFragment = ResultFragment.newInstance(ArrayList(stockResponseList))
                resultFragment.setStocks(stockResponseList)
                openFragments(resultFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_actions -> {
                toolbar.title = getString(R.string.actions)
                val actionsFragment = ActionsFragment.newInstance(mUser)
                openFragments(actionsFragment)
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
                startActivity(Intent(this, LoggedOutActivity::class.java))
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
