package com.example.netbenefitsapp.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.datasource.remote.StockCallback
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import com.example.netbenefitsapp.view.adapters.StockListAdapter
import com.example.netbenefitsapp.viewmodel.market.MarketViewModel

class MarketFragment : Fragment(), StockCallback {

    private lateinit var viewModel: MarketViewModel
    private lateinit var rvStockList : RecyclerView
    private lateinit var stockListAdapter : StockListAdapter

    companion object {
        fun newInstance() = MarketFragment()
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MarketViewModel::class.java)

        viewModel.getStockResponseList(this)
    }

    override fun stockCallbackResult(stockResponseList: List<StockResponse>) {

        stockListAdapter = StockListAdapter(stockResponseList)
        rvStockList.adapter = stockListAdapter

        Log.d("TAG_RESULT", stockResponseList[0].date)
        Log.d("TAG_RESULT", stockResponseList[0].open.toString())
        Log.d("TAG_RESULT", stockResponseList[0].close.toString())

        Log.d("TAG_RESULT", stockResponseList[1].date)
        Log.d("TAG_RESULT", stockResponseList[1].open.toString())
        Log.d("TAG_RESULT", stockResponseList[1].close.toString())

        Log.d("TAG_RESULT", stockResponseList[2].date)
        Log.d("TAG_RESULT", stockResponseList[2].open.toString())
        Log.d("TAG_RESULT", stockResponseList[2].close.toString())
    }

}
