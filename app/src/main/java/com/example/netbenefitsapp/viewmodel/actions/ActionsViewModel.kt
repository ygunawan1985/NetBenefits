package com.example.netbenefitsapp.viewmodel.actions

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel;
import com.example.netbenefitsapp.model.Photo
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.view.activities.changepassword.ChangePasswordActivity
import com.example.netbenefitsapp.view.activities.googlemap.CompanyMapsActivity
import com.example.netbenefitsapp.view.activities.loggedout.LoggedOutActivity
import com.example.netbenefitsapp.view.activities.updateprofile.UpdateProfileActivity
import com.example.netbenefitsapp.view.activities.webview.LibraryWebViewActivity
import com.google.firebase.auth.FirebaseAuth

class ActionsViewModel : ViewModel() {
    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private val MUTUAL_FUND_URL = "https://www.fidelity.com/tax-information/fidelity-mutual-fund-tax-information"
    private val TAX_INFORMATION_URL = "https://institutional.fidelity.com/app/item/RD_13569_14965/tax-information-center.html"

    fun logout(view : View) {
        mAuth.signOut()
        val logoutIntent = Intent(view.context, LoggedOutActivity::class.java)
        view.context.startActivity(logoutIntent)
    }

    fun openMutualFund(view: View) {
        val mutualIntent = Intent(view.context, LibraryWebViewActivity::class.java)
        mutualIntent.putExtra("libraryUrl", MUTUAL_FUND_URL)
        view.context.startActivity(mutualIntent)
    }

    fun openTaxInformation(view: View) {
        val taxIntent = Intent(view.context, LibraryWebViewActivity::class.java)
        taxIntent.putExtra("libraryUrl", TAX_INFORMATION_URL)
        view.context.startActivity(taxIntent)
    }

    fun changePassword(view: View) {
        val changePasswordIntent = Intent(view.context, ChangePasswordActivity::class.java)
        view.context.startActivity(changePasswordIntent)
    }

    fun updateProfile(
        view: View,
        mUser: User?,
        photos: ArrayList<Photo>?
    ) {
        val updateIntent = Intent(view.context, UpdateProfileActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelableArrayList("photos", photos)
        bundle.putParcelable("user", mUser)
        updateIntent.putExtra("bundle", bundle)
        view.context.startActivity(updateIntent)
    }

    fun showCompanyMap(view: View, mUser: User?) {
        val companyMapsIntent = Intent(view.context, CompanyMapsActivity::class.java)
        companyMapsIntent.putExtra("user", mUser)
        view.context.startActivity(companyMapsIntent)
    }
}
