package com.sky.currencyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sky.currencyapp.R
import com.sky.currencyapp.model.CurrencyDetails
import kotlinx.android.synthetic.main.currencies_activity_recycler_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class CurrenciesRecyclerAdapter(private val listCurrency: ArrayList<CurrencyDetails>) : RecyclerView.Adapter<CurrenciesRecyclerAdapter.CurrenciesHolder>() {
    class CurrenciesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.currencies_activity_recycler_row,parent,false)
        return CurrenciesHolder(view)
    }

    override fun onBindViewHolder(holder: CurrenciesHolder, position: Int) {

            holder.itemView.currenciesRecyclerRow_currencyNameTextView.text =
                listCurrency[position].name
            holder.itemView.currenciesRecyclerRow_currencyCodeTextView.text =
                listCurrency[position].code
            holder.itemView.currenciesRecyclerRow_decimalUnitTextView.text =
                listCurrency[position].decimalUnits


        holder.itemView.currenciesRecyclerRow_checkboxFavorite.setOnClickListener {
            if (holder.itemView.currenciesRecyclerRow_checkboxFavorite.isChecked) {
                listCurrency[position].isFavorite = true
                Toast.makeText(it.context, "Favorilendi", Toast.LENGTH_SHORT).show()
            } else {
                listCurrency[position].isFavorite = false
                //Toast.makeText(it.context, "Favorilerden çıkarıldı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return listCurrency.size
    }

    fun refreshData(newCurrency: List<CurrencyDetails> )
    {
        listCurrency.clear()
        listCurrency.addAll(newCurrency)
        notifyDataSetChanged()
    }

}