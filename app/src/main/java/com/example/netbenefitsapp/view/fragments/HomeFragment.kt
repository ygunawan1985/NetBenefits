package com.example.netbenefitsapp.view.fragments

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.User
import com.example.netbenefitsapp.viewmodel.home.HomeViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeFragment : Fragment(), HomeViewModel.MyCallback {

    private lateinit var tvPortfolioBalance : TextView
    private lateinit var tvSavingsBalance : TextView
    private lateinit var tvTodaysDate : TextView

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvPortfolioBalance = view.findViewById(R.id.tvPortfolioBalance)
        tvSavingsBalance = view.findViewById(R.id.tvSavingsBalance)
        tvTodaysDate = view.findViewById(R.id.tvTodaysDate)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getUser(this)
    }

    override fun onCallback(user: User) {
        val portBalance = "$" + "%.2f".format(user.balance / 100.toFloat())
        tvPortfolioBalance.text = portBalance
        val savingsBalance = "$" + "%.2f".format(user.balance / 100.toFloat())
        tvSavingsBalance.text = savingsBalance
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mma")
            var answer: String = "As of " + current.format(formatter)
            tvTodaysDate.text = answer
        } else {
            var date = Date();
            val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
            val answer: String = "As of " + formatter.format(date)
            tvTodaysDate.text = answer
        }

    }

}
