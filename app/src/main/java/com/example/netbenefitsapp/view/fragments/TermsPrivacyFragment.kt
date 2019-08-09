package com.example.netbenefitsapp.view.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.netbenefitsapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TermsPrivacyFragment : Fragment(), View.OnClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var tvTermsOfUse : TextView
    private lateinit var tvPrivacy : TextView
    private lateinit var tvSecurity : TextView
    private lateinit var tvAccessibility : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms_privacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTermsOfUse = view.findViewById(R.id.tvTermsOfUse)
        tvTermsOfUse.setOnClickListener(this)
        tvPrivacy = view.findViewById(R.id.tvPrivacy)
        tvPrivacy.setOnClickListener(this)
        tvSecurity = view.findViewById(R.id.tvSecurity)
        tvSecurity.setOnClickListener(this)
        tvAccessibility = view.findViewById(R.id.tvAccessibility)
        tvAccessibility.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.tvTermsOfUse -> {
                listener?.onFragmentInteraction("termsOfUse")
                val termsOfUseIntent = Intent(Intent.ACTION_VIEW)
                termsOfUseIntent.setData(Uri.parse("https://www.fidelity.com/terms-of-use"))
                startActivity(termsOfUseIntent)
            }
            R.id.tvPrivacy -> {
                listener?.onFragmentInteraction("privacy")
                val privacyIntent = Intent(Intent.ACTION_VIEW)
                privacyIntent.setData(Uri.parse("https://www.fidelity.com/privacy-policy"))
                startActivity(privacyIntent)
            }
            R.id.tvSecurity -> {
                listener?.onFragmentInteraction("security")
                val securityIntent = Intent(Intent.ACTION_VIEW)
                securityIntent.setData(Uri.parse("https://www.fidelity.com/security/overview"))
                startActivity(securityIntent)
            }
            R.id.tvAccessibility -> {
                listener?.onFragmentInteraction("accessibility")
                val accessibilityIntent = Intent(Intent.ACTION_VIEW)
                accessibilityIntent.setData(Uri.parse("https://www.fidelity.com/accessibility/overview"))
                startActivity(accessibilityIntent)
            }
        }
    }

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(passedString : String)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TermsPrivacyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
