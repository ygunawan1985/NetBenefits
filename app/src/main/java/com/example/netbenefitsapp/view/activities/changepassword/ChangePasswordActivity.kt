package com.example.netbenefitsapp.view.activities.changepassword

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.viewmodel.changepassword.ChangePasswordViewModel

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var etChangePassword : EditText
    private lateinit var viewModel : ChangePasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.color_green)))
        supportActionBar?.title = "Change Password"

        etChangePassword = findViewById(R.id.etChangePassword)
        viewModel = ChangePasswordViewModel()
    }

    fun onClicks(view : View) {
        viewModel.changePassword(this, etChangePassword.text.toString())
    }
}
