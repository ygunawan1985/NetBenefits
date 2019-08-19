package com.example.netbenefitsapp.viewmodel.changepassword

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.view.activities.loggedout.LoggedOutActivity
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordViewModel : ViewModel() {
    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

    fun changePassword(context : Context, newPassword: String) {
        val mUser = mAuth.currentUser
        mUser?.updatePassword(newPassword)
        mAuth.signOut()
        val logoutIntent = Intent(context, LoggedOutActivity::class.java)
        context.startActivity(logoutIntent)
    }

}