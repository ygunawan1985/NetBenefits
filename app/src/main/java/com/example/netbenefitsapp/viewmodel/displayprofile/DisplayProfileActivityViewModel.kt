package com.example.netbenefitsapp.viewmodel.displayprofile

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.view.activities.main.MainActivity

class DisplayProfileActivityViewModel : ViewModel() {

    private lateinit var user: User
    lateinit var userName : String
    lateinit var email : String
    lateinit var firstName : String
    lateinit var lastName : String
    lateinit var ssn : String
    lateinit var company: String
    lateinit var insurance : String
    lateinit var balance : String

    fun updateProfile(view : View) {
        Toast.makeText(view.context, "Updating profile...", Toast.LENGTH_SHORT).show()
    }

    fun goToHome(view: View) {
        view.context.startActivity(Intent(view.context, MainActivity::class.java))
    }

    fun setUserInfo(user: User) {
        this.user = user
        this.userName = user.userName.toString()
        this.email = user.email.toString()
        this.firstName = user.firstName.toString()
        this.lastName = user.lastName.toString()
        this.ssn = user.ssn.toString()
        this.company = user.company.toString()
        this.insurance = user.insurance.toString()
        this.balance = ((user.balance / 100.toDouble()).toString())

    }


}