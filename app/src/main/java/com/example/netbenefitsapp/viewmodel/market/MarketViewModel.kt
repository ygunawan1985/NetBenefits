package com.example.netbenefitsapp.viewmodel.market

import androidx.lifecycle.ViewModel
import com.example.netbenefitsapp.model.datasource.remote.RetrofitHelper
import com.example.netbenefitsapp.model.datasource.remote.StockCallback
import com.example.netbenefitsapp.model.datasource.remote.StockObserver
import com.example.netbenefitsapp.model.datasource.remote.UrlConstants.Companion.API_TOKEN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MarketViewModel : ViewModel() {
    fun getStockResponseList(callback: StockCallback) {
        RetrofitHelper()
            .getStockService()
            .getStockResponse("2019-07-28", "2019-08-11", API_TOKEN, "d", "json")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(StockObserver(callback))
    }
}
