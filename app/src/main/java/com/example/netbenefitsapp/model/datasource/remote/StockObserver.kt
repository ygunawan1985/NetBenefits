package com.example.netbenefitsapp.model.datasource.remote

import android.util.Log
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class StockObserver(private var stockCallback: StockCallback) : Observer<List<StockResponse>> {

    lateinit var stockResponseList : List<StockResponse>

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(stockResponseList: List<StockResponse>) {
        this.stockResponseList = stockResponseList
    }

    override fun onError(e: Throwable) {
        Log.e("TAG_ERROR", "in onError", e)
    }

    override fun onComplete() {
        stockCallback.stockCallbackResult(stockResponseList)
    }
}