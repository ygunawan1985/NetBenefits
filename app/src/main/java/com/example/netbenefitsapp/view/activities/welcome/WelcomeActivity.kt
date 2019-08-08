package com.example.netbenefitsapp.view.activities.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.databinding.ActivityWelcomeBinding
import com.example.netbenefitsapp.view.activities.newuser.NewUserRegistration
import com.example.netbenefitsapp.view.fragments.TermsPrivacyFragment
import com.example.netbenefitsapp.viewmodel.welcome.WelcomeActivityViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity(), TermsPrivacyFragment.OnFragmentInteractionListener, View.OnClickListener {

    private val fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var termsPrivacyFragment : TermsPrivacyFragment
    private lateinit var tvNewUser : TextView
    private lateinit var welcomeActivityViewModel : WelcomeActivityViewModel
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

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

//    override fun onStart() {
//        super.onStart()
//
//        val currentUser : FirebaseUser? = mAuth.currentUser
//        updateUI(currentUser)
//    }

//    private fun updateUI(currentUser: FirebaseUser?) {
//        val userEmail : String? = currentUser?.email
//        tvCurrentUser.text = userEmail
//
//        if(currentUser != null){
//            tvLogout.text = "Logout"
//            tvLogout.setOnClickListener(this)
//        } else {
//            tvLogout.text = ""
//        }
//    }

    private fun setupAndAddFragment() {
        termsPrivacyFragment = TermsPrivacyFragment()
        fragmentManager.beginTransaction().add(R.id.frmTermsFragment, termsPrivacyFragment).addToBackStack("TERMS_PRIVACY_FRAGMENT").commit()
    }

    override fun onClick(view : View){
        when(view.id) {
            R.id.tvNewUser -> startActivity(Intent(this, NewUserRegistration::class.java))
        }
    }

//    private fun signOutOfAccountClicked(view : View) {
//        mAuth.signOut()
//        updateUI(null)
//    }

    override fun onFragmentInteraction(passedString: String) {
        Toast.makeText(applicationContext, passedString, Toast.LENGTH_SHORT).show()
        Log.d("TAG_FRAGMENT", passedString)

        //when statement

    }
}
