package com.example.netbenefitsapp.viewmodel.logout

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel;
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth

class LogoutViewModel : ViewModel() {
    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

    fun logout(view : View) {
        mAuth.signOut()
        val logoutIntent = Intent(view.context, WelcomeActivity::class.java)
        view.context.startActivity(logoutIntent)
    }

}
