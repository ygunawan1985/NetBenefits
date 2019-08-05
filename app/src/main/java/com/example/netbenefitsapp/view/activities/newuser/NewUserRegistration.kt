package com.example.netbenefitsapp.view.activities.newuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class NewUserRegistration : AppCompatActivity(), View.OnClickListener {

    private lateinit var etEmailNewUser : EditText
    private lateinit var etPasswordNewUser : EditText
    private lateinit var etFirstName : EditText
    private lateinit var etLastName : EditText
    private lateinit var etSocialSecurity: EditText
    private lateinit var btnSubmit : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user_registration)

        mAuth = FirebaseAuth.getInstance()
        etEmailNewUser = findViewById(R.id.etEmailNewUser)
        etPasswordNewUser = findViewById(R.id.etPasswordNewUser)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etSocialSecurity = findViewById(R.id.etSocialSecurity)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSubmit -> {
                val email = etEmailNewUser.text.toString()
                val password = etPasswordNewUser.text.toString()
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val ssn = etSocialSecurity.text.toString()
                createAccountUsingFirebase(email, password)
            }
        }
    }

    private fun createAccountUsingFirebase(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    startActivity(Intent(this, WelcomeActivity::class.java))

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "FAILED CREATING ACCOUNT", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
