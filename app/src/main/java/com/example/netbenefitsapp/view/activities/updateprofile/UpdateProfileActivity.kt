package com.example.netbenefitsapp.view.activities.updateprofile

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.Photo
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.viewmodel.updateprofile.UpdateProfileViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_update_profile.*

class UpdateProfileActivity : AppCompatActivity() {

    private var mUser : User? = null
    private var photos : ArrayList<Photo>? = null
    private lateinit var ivUserPhoto : ImageView
    private lateinit var etFirstName : EditText
    private lateinit var etLastName : EditText
    private lateinit var etSocialSecurity : EditText
    private lateinit var etInsurance : EditText
    private lateinit var viewModel : UpdateProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.color_green)))
        supportActionBar?.title = getString(R.string.update_profile)

        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etSocialSecurity = findViewById(R.id.etSocialSecurity)
        etInsurance = findViewById(R.id.etInsurance)
        viewModel = UpdateProfileViewModel()

        val receivedIntent = intent
        val bundle = receivedIntent.getBundleExtra("bundle")
        mUser = bundle.getParcelable("user")
        photos = bundle.getParcelableArrayList("photos")

        ivUserPhoto = findViewById(R.id.ivUserPhoto)
        var photoUrl : String? = null
        photos?.forEach{
            if (it.name.equals(mUser?.firstName)){
                photoUrl = it.url
            }
        }

        Picasso.get().load(photoUrl).into(ivUserPhoto)
        etFirstName.setText(mUser?.firstName)
        etLastName.setText(mUser?.lastName)
        etSocialSecurity.setText(mUser?.ssn)
        etInsurance.setText(mUser?.insurance)

    }

    fun onClick(view : View){
        mUser?.firstName = etFirstName.text.toString()
        mUser?.lastName = etLastName.text.toString()
        mUser?.ssn = etSocialSecurity.text.toString()
        mUser?.insurance = etInsurance.text.toString()
        viewModel.updateProfile(mUser, view)
    }

}
