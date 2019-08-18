package com.example.netbenefitsapp.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.viewmodel.actions.ActionsViewModel

private const val ARG_USER = "user"

class ActionsFragment : Fragment() {

    private lateinit var viewModel: ActionsViewModel
    private var mUser : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mUser = it.getParcelable(ARG_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ActionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(param1 : User?) =
            ActionsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, param1)
                }
            }
    }

}
