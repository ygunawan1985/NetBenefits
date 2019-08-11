package com.example.netbenefitsapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import kotlinx.android.synthetic.main.stock_item.view.*

class StockListAdapter(private val stockResponseList : List<StockResponse>) : RecyclerView.Adapter<StockListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false))

    override fun getItemCount(): Int = stockResponseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.setValues(stockResponseList[position])

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun setValues(stockResponse : StockResponse) {
            itemView.tvDate.text = stockResponse.date
            itemView.tvLow.text = stockResponse.low.toString()
            itemView.tvHigh.text = stockResponse.high.toString()
            itemView.tvOpen.text = stockResponse.open.toString()
            itemView.tvClose.text = stockResponse.close.toString()
        }
    }
}