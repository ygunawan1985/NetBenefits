package com.example.netbenefitsapp.viewmodel.welcome

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.view.activities.main.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivityViewModel : ViewModel() {

    private lateinit var email : String
    private lateinit var password : String
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onEmailChanged(email : Editable) {
        this.email = email.toString()
        Log.d("TAG_VIEWMODEL", this.email)
    }

    fun onPasswordChanged(password : Editable) {
        this.password = password.toString()
        Log.d("TAG_VIEWMODEL", this.password)
    }

    fun signInUsingFirebase(view : View) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    //updateUI(user!!)
                    var nextIntent = Intent(view.context, MainActivity::class.java)
                    nextIntent.putExtra("user", user)
                    view.context.startActivity(nextIntent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(view.context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }

    }

}

