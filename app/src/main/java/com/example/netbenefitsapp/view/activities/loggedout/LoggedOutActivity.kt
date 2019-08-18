package com.example.netbenefitsapp.view.activities.loggedout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.databinding.ActivityLoggedOutBinding
import com.example.netbenefitsapp.viewmodel.loggedout.LoggedOutViewModel

class LoggedOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_logged_out)

        val loggedOutViewModel = LoggedOutViewModel()
        val loggedOutBinding : ActivityLoggedOutBinding = DataBindingUtil.setContentView(this, R.layout.activity_logged_out)
        loggedOutBinding.viewModel = loggedOutViewModel
    }
}
