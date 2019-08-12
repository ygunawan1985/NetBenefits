package com.example.netbenefitsapp.viewmodel.home

import android.util.Log
import androidx.lifecycle.ViewModel;
import com.example.netbenefitsapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeViewModel : ViewModel() {

    private val mDatabase : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = mDatabase.getReference("users")

    fun getUser(myCallback : MyCallback) {

        val userEmail = FirebaseAuth.getInstance().currentUser?.email
        val index = userEmail!!.indexOf('@')
        val userName = userEmail?.substring(0, index)
        var user = User()

        val loggedInUserRef = usersRef.child(userName)
        loggedInUserRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)!!
                myCallback.onCallback(user)
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }

        })
    }

    interface MyCallback {
        fun onCallback(user: User)
    }
}
