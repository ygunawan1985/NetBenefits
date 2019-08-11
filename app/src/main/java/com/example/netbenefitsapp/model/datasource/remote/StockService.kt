package com.example.netbenefitsapp.model.datasource.remote

import com.example.netbenefitsapp.model.datasource.remote.UrlConstants.Companion.QUERY_API_TOKEN
import com.example.netbenefitsapp.model.datasource.remote.UrlConstants.Companion.QUERY_FMT
import com.example.netbenefitsapp.model.datasource.remote.UrlConstants.Companion.QUERY_FROM
import com.example.netbenefitsapp.model.datasource.remote.UrlConstants.Companion.QUERY_PERIOD
import com.example.netbenefitsapp.model.datasource.remote.UrlConstants.Companion.QUERY_TO
import com.example.netbenefitsapp.model.datasource.remote.UrlConstants.Companion.SBUX_PATH
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {

    @GET(SBUX_PATH)
    fun getStockResponse(
        @Query(QUERY_FROM) from : String,
        @Query(QUERY_TO) to : String,
        @Query(QUERY_API_TOKEN) api_token : String,
        @Query(QUERY_PERIOD) period : String,
        @Query(QUERY_FMT) fmt : String
    ) : Observable<List<StockResponse>>

}