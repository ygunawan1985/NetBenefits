package com.example.netbenefitsapp.viewmodel.newuser

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import android.text.Editable
import android.view.View
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewUserRegistrationViewModel : ViewModel() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var newUserEmail : String
    private lateinit var password : String
    private lateinit var firstName : String
    private lateinit var lastName : String
    private lateinit var ssn : String

    fun onUserEmailChanged(newUserEmail : Editable) {
        this.newUserEmail = newUserEmail.toString()
    }

    fun onPasswordChanged(password : Editable) {
        this.password = password.toString()
    }

    fun onFirstNameChanged(firstName : Editable) {
        this.firstName = firstName.toString()
    }

    fun onLastNameChanged(lastName : Editable) {
        this.lastName = lastName.toString()
    }

    fun onSsnChanged(ssn : Editable) {
        this.ssn = ssn.toString()
    }

    fun createAccountUsingFirebase(view : View) {
        mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(newUserEmail, password)
            .addOnCompleteListener { task : Task<AuthResult> ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")

                    val index = newUserEmail.indexOf('@')
                    val userName = newUserEmail.substring(0, index)
                    val user = User(userName, newUserEmail, firstName, lastName, ssn, "Mobile Apps Company", "Blue Cross Blue Shield", 350000, "https://i.imgur.com/1Zxaz88.jpg")
                    addNewUser(user)

                    mAuth.signOut()
                    Toast.makeText(view.context, "New account is created", Toast.LENGTH_SHORT).show()
                    view.context.startActivity(Intent(view.context, WelcomeActivity::class.java))

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(view.context, "FAILED CREATING ACCOUNT", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addNewUser(user : User) {
        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("users").child(user.userName.toString()).setValue(user)
    }

}