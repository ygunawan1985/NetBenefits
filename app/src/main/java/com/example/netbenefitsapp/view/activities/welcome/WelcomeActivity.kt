package com.example.netbenefitsapp.view.activities.welcome

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.databinding.ActivityWelcomeBinding
import com.example.netbenefitsapp.view.activities.newuser.NewUserRegistration
import com.example.netbenefitsapp.view.fragments.TermsPrivacyFragment
import com.example.netbenefitsapp.viewmodel.welcome.WelcomeActivityViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class WelcomeActivity : AppCompatActivity(), TermsPrivacyFragment.OnFragmentInteractionListener, View.OnClickListener {

    private val FIDELITY_ICON : String = "https://i.imgur.com/FUpOocB.jpg"
    private val fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var termsPrivacyFragment : TermsPrivacyFragment
    private lateinit var tvNewUser : TextView
    private lateinit var welcomeActivityViewModel : WelcomeActivityViewModel
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.color_green)))

        //Firebase auth
        mAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)

        welcomeActivityViewModel = WelcomeActivityViewModel()
        val binding : ActivityWelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.viewModel = welcomeActivityViewModel

        tvNewUser = findViewById(R.id.tvNewUser)
        tvNewUser.setOnClickListener(this)

        setupAndAddFragment()
    }

    private fun setupAndAddFragment() {
        termsPrivacyFragment = TermsPrivacyFragment()
        fragmentManager.beginTransaction().add(R.id.frmTermsFragment, termsPrivacyFragment).addToBackStack("TERMS_PRIVACY_FRAGMENT").commit()
    }

    override fun onClick(view : View){
        when(view.id) {
            R.id.tvNewUser -> startActivity(Intent(this, NewUserRegistration::class.java))
        }
    }

    override fun onFragmentInteraction(passedString: String) {
        Toast.makeText(applicationContext, passedString, Toast.LENGTH_SHORT).show()
        Log.d("TAG_FRAGMENT", passedString)

        //when statement

    }
}
