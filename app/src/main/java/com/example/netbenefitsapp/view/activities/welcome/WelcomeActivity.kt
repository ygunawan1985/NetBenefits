package com.example.netbenefitsapp.view.activities.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.view.activities.newuser.NewUserRegistration
import com.example.netbenefitsapp.view.fragments.TermsPrivacyFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class WelcomeActivity : AppCompatActivity(), TermsPrivacyFragment.OnFragmentInteractionListener, View.OnClickListener {

    private val fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var termsPrivacyFragment : TermsPrivacyFragment
    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var tvNewUser : TextView
    private lateinit var tvCurrentUser: TextView
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //Firebase auth
        mAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)

        tvNewUser = findViewById(R.id.tvNewUser)
        tvNewUser.setOnClickListener(this)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        tvCurrentUser = findViewById(R.id.tvCurrentUser)
        setupAndAddFragment()
    }

    override fun onStart() {
        super.onStart()

        val currentUser : FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    //Not sure if this is needed
    private fun updateUI(currentUser: FirebaseUser?) {
        val userEmail : String? = currentUser?.email
        tvCurrentUser.text = currentUser?.email
    }

    private fun setupAndAddFragment() {
        termsPrivacyFragment = TermsPrivacyFragment()
        fragmentManager.beginTransaction().add(R.id.frmTermsFragment, termsPrivacyFragment).addToBackStack("TERMS_PRIVACY_FRAGMENT").commit()
    }

    override fun onClick(view : View){
        when(view.id) {
            R.id.tvNewUser -> startActivity(Intent(this, NewUserRegistration::class.java))
            R.id.btnLogin ->
                {
                    val email = etEmail.text.toString()
                    val password = etPassword.text.toString()
                    if (!(email.isEmpty() || password.isEmpty())) {
                        signInUsingFirebase(email, password)
                    }
                }

        }

//        val email = etEmail.text.toString()
//        val password = etPassword.text.toString()

    }

    private fun signInUsingFirebase(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    updateUI(user!!)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onFragmentInteraction(passedString: String) {
        Toast.makeText(applicationContext, passedString, Toast.LENGTH_SHORT).show()
        Log.d("TAG_FRAGMENT", passedString)

        //when statement

    }
}
