package com.example.netbenefitsapp.model.datasource.remote

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StockRepo {

    companion object {
        fun getStockList(stockCallback: StockCallback) {
            RetrofitHelper()
                .getStockService()
                .getStockResponse("2019-08-01", "2019-08-18", UrlConstants.API_TOKEN, "d", "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(StockObserver(stockCallback))
        }
    }
}