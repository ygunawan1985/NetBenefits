package com.example.netbenefitsapp.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.Photo
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.viewmodel.actions.ActionsViewModel

private const val ARG_USER = "user"

class ActionsFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: ActionsViewModel
    private var mUser : User? = null
    private var photos : ArrayList<Photo>? = null
    private lateinit var btnMutualFund : TextView
    private lateinit var btnTaxInformation : TextView
    private lateinit var btnCompanyMap : TextView
    private lateinit var btnUpdateProfile : TextView
    private lateinit var btnChangePassword : TextView
    private lateinit var btnLogout : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mUser = it.getParcelable(ARG_USER)
            photos = it.getParcelableArrayList("photos")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actions_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMutualFund = view.findViewById(R.id.btnMutualFund)
        btnMutualFund.setOnClickListener(this)
        btnTaxInformation = view.findViewById(R.id.btnTaxInformation)
        btnTaxInformation.setOnClickListener(this)
        btnCompanyMap = view.findViewById(R.id.btnCompanyMap)
        btnCompanyMap.setOnClickListener(this)
        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile)
        btnUpdateProfile.setOnClickListener(this)
        btnChangePassword = view.findViewById(R.id.btnChangePassword)
        btnChangePassword.setOnClickListener(this)
        btnLogout = view.findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ActionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnMutualFund -> viewModel.openMutualFund(view)
            R.id.btnTaxInformation -> viewModel.openTaxInformation(view)
            R.id.btnCompanyMap -> viewModel.showCompanyMap(view, mUser)
            R.id.btnUpdateProfile -> viewModel.updateProfile(view, mUser, photos)
            R.id.btnChangePassword -> viewModel.changePassword(view)
            R.id.btnLogout -> viewModel.logout(view)
        }
    }

    companion object {
        fun newInstance(
            param1: User?,
            photos: ArrayList<Photo>
        ) =
            ActionsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, param1)
                    putParcelableArrayList("photos", photos)
                }
            }
    }

}
