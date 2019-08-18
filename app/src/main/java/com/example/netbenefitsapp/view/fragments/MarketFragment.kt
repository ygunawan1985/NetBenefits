package com.example.netbenefitsapp.view.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.datasource.remote.StockCallback
import com.example.netbenefitsapp.model.datasource.remote.StockRepo
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import com.example.netbenefitsapp.view.adapters.StockListAdapter
import com.example.netbenefitsapp.viewmodel.market.MarketViewModel

class MarketFragment : Fragment(), StockCallback {

    private lateinit var viewModel: MarketViewModel
    private lateinit var rvStockList : RecyclerView
    //private lateinit var stockListAdapter : StockListAdapter
    private lateinit var mListener : OnFragmentInteractionListener

    companion object {
        fun newInstance() = MarketFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        StockRepo.getStockList(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.market_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvStockList = view.findViewById(R.id.rvStockMarket)
        val mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvStockList.layoutManager = mLayoutManager
        rvStockList.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(MarketViewModel::class.java)
        viewModel.getStockResponseList(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun stockCallbackResult(stockResponseList: List<StockResponse>) {
        mListener.onFragmentInteraction(stockResponseList)
    }

    interface OnFragmentInteractionListener{
        fun onFragmentInteraction(stockResponseList : List<StockResponse>)
    }

}
