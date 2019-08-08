package com.example.netbenefitsapp.view.activities.newuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.databinding.ActivityNewUserRegistrationBinding
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity
import com.example.netbenefitsapp.viewmodel.newuser.NewUserRegistrationViewModel
import com.google.firebase.auth.FirebaseAuth

class NewUserRegistration : AppCompatActivity() {

    private lateinit var newUserRegistrationViewModel : NewUserRegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user_registration)

        newUserRegistrationViewModel = NewUserRegistrationViewModel()
        val registrationBinding : ActivityNewUserRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_user_registration)
        registrationBinding.viewModel = newUserRegistrationViewModel
    }

}
