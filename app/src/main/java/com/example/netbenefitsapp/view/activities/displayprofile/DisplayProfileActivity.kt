package com.example.netbenefitsapp.view.activities.displayprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.databinding.ActivityDisplayProfileBinding
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.viewmodel.displayprofile.DisplayProfileActivityViewModel

class DisplayProfileActivity : AppCompatActivity() {

    private lateinit var displayProfileActivityViewModel : DisplayProfileActivityViewModel
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_profile)

        val receivedIntent = intent
        val bundle = receivedIntent.getBundleExtra("bundle")
        user = bundle.getParcelable("user")!!

        displayProfileActivityViewModel = DisplayProfileActivityViewModel()
        var displayProfileBinding : ActivityDisplayProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_display_profile)
        displayProfileBinding.viewModel = displayProfileActivityViewModel

        displayProfileActivityViewModel.setUserInfo(user)
    }
}
