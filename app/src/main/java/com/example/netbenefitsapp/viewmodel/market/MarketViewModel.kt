package com.example.netbenefitsapp.viewmodel.market

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.model.datasource.remote.StockCallback
import com.example.netbenefitsapp.model.datasource.remote.StockRepo

class MarketViewModel : ViewModel() {
    fun getStockResponseList(context: StockCallback){
        StockRepo.getStockList(context)
    }
}
