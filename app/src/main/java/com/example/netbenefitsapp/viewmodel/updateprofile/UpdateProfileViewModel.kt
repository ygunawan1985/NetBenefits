package com.example.netbenefitsapp.viewmodel.updateprofile

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateProfileViewModel : ViewModel() {

    private lateinit var database : DatabaseReference

    fun updateProfile(mUser : User?, view : View) {
        database = FirebaseDatabase.getInstance().reference
        database.child("users").child(mUser?.userName.toString()).setValue(mUser)

        Toast.makeText(view.context, "Profile Updated!", Toast.LENGTH_LONG).show()
    }
}