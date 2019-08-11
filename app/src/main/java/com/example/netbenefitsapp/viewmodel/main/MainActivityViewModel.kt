package com.example.netbenefitsapp.viewmodel.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.view.activities.displayprofile.DisplayProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivityViewModel : ViewModel() {
    private val mDatabase : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = mDatabase.getReference("users")

    fun displayProfile(context: Context) {
        getUser(object : MyCallback {
            override fun onCallback(user: User) {
                val userIntent = Intent(context, DisplayProfileActivity::class.java)
                val bundle = Bundle()
                var mUser = User(user.userName.toString(),
                    user.email.toString(),
                    user.firstName.toString(), user.lastName.toString(),
                    user.ssn.toString(), user.company.toString(), user.insurance.toString(), user.balance)
                bundle.putParcelable("user", mUser)
                userIntent.putExtra("bundle", bundle)
                context.startActivity(userIntent)
            }
        })

    }

    fun getUser(myCallback : MyCallback) {

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