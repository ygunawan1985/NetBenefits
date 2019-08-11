package com.example.netbenefitsapp.model.datasource.remote

import com.example.netbenefitsapp.model.stockresponse.StockResponse

interface StockCallback {
    fun stockCallbackResult(stockResponseList : List<StockResponse>)
}