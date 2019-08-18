package com.example.netbenefitsapp.viewmodel.welcome

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.view.activities.displayprofile.DisplayProfileActivity
import com.example.netbenefitsapp.view.activities.main.MainActivity
import com.example.netbenefitsapp.view.activities.splashscreen.SplashScreenActivity
import com.example.netbenefitsapp.viewmodel.main.MainActivityViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class WelcomeActivityViewModel : ViewModel() {

    private var email : String = ""
    private var password : String = ""
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val mDatabase : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = mDatabase.getReference("users")

    fun onEmailChanged(email : Editable) {
        this.email = email.toString()
        Log.d("TAG_VIEWMODEL", this.email)
    }

    fun onPasswordChanged(password : Editable) {
        this.password = password.toString()
        Log.d("TAG_VIEWMODEL", this.password)
    }

    fun signInUsingFirebase(view : View) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        val firebaseUser = mAuth.currentUser

                        getUser(object : WelcomeActivityViewModel.MyCallback {
                            override fun onCallback(user: User) {
                                val userIntent = Intent(view.context, SplashScreenActivity::class.java)
                                val bundle = Bundle()
                                var mUser = User(user.userName.toString(),
                                    user.email.toString(),
                                    user.firstName.toString(), user.lastName.toString(),
                                    user.ssn.toString(), user.company.toString(), user.insurance.toString(), user.balance, user.companyLogoUrl.toString())
                                bundle.putParcelable("user", mUser)
                                bundle.putParcelable("firebaseUser", firebaseUser)
                                userIntent.putExtra("bundle", bundle)
                                view.context.startActivity(userIntent)
                            }
                        })

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(view.context, "Authentication failed. Wrong login info", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(view.context, "Enter email / password to login", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUser(myCallback : MyCallback) {

        val userEmail = FirebaseAuth.getInstance().currentUser?.email
        val index = userEmail!!.indexOf('@')
        val userName = userEmail?.substring(0, index)
        var user = User()

        val loggedInUserRef = usersRef.child(userName)
        loggedInUserRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)!!
                Log.d("TAG_USER", dataSnapshot.value.toString())
                myCallback.onCallback(user)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    interface MyCallback {
        fun onCallback(user: User)
    }

}

