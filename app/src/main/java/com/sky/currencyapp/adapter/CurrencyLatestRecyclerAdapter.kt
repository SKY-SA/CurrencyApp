package com.sky.currencyapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.currencyapp.R
import com.sky.currencyapp.model.CurrencyLatest
import com.sky.currencyapp.view.CurrenciesActivity
import kotlinx.android.synthetic.main.latest_activity_recycler_row.view.*

class CurrencyLatestRecyclerAdapter(val listCurrency : ArrayList<CurrencyLatest>): RecyclerView.Adapter<CurrencyLatestRecyclerAdapter.CurrencyVH>() {
    class CurrencyVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.latest_activity_recycler_row,parent,false)
        return CurrencyVH(view)
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.itemView.latestRecyclerRow_textView.text = listCurrency[position].fullName
        holder.itemView.latestRecyclerRow_radioButton.text = listCurrency[position].name!!.uppercase()
    }

    override fun getItemCount(): Int {
        return listCurrency.size
    }
    fun refreshData(newCurrency: List<CurrencyLatest> )
    {
        listCurrency.clear()
        listCurrency.addAll(newCurrency)
        notifyDataSetChanged()
    }
}