package com.example.netbenefitsapp.viewmodel.loggedout

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity

class LoggedOutViewModel : ViewModel() {

    fun loginAgain(view : View) {
        view.context.startActivity(Intent(view.context, WelcomeActivity::class.java))
    }

}