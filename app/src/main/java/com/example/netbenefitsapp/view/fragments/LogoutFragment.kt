package com.example.netbenefitsapp.view.fragments

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.view.activities.welcome.WelcomeActivity
import com.example.netbenefitsapp.viewmodel.logout.LogoutViewModel
import com.google.firebase.auth.FirebaseAuth

class LogoutFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: LogoutViewModel
    private lateinit var tvDisclosures : TextView
    private lateinit var tvContactUs : TextView
    private lateinit var tvLogout : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.logout_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LogoutViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvDisclosures = view.findViewById(R.id.tvDisclosures)
        tvDisclosures.setOnClickListener(this)
        tvContactUs = view.findViewById(R.id.tvContactUs)
        tvContactUs.setOnClickListener(this)
        tvLogout = view.findViewById(R.id.tvLogout)
        tvLogout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.tvDisclosures -> {
                val disclosureIntent = Intent(Intent.ACTION_VIEW)
                disclosureIntent.setData(Uri.parse("https://www.fidelity.com/retirement-ira/small-business/participant-disclosure-regulations-summary"))
                startActivity(disclosureIntent)
            }
            R.id.tvContactUs -> {
                val contactUsIntent = Intent(Intent.ACTION_VIEW)
                contactUsIntent.setData(Uri.parse("https://www.fidelity.com/customer-service/contact-us"))
                startActivity(contactUsIntent)
            }
            R.id.tvLogout -> viewModel.logout(view)
        }
    }

    companion object {
        fun newInstance() = LogoutFragment()
    }
}
