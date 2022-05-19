package com.sky.currencyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sky.currencyapp.R
import com.sky.currencyapp.model.CurrencyDetails
import kotlinx.android.synthetic.main.currencies_activity_recycler_row.view.*
import kotlin.collections.ArrayList

class CurrenciesRecyclerAdapter(private val listCurrency: ArrayList<CurrencyDetails>) : RecyclerView.Adapter<CurrenciesRecyclerAdapter.CurrenciesHolder>() {
    class CurrenciesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private val listFavCurCode = ArrayList<String?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.currencies_activity_recycler_row, parent, false)
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
            if (holder.itemView.currenciesRecyclerRow_checkboxFavorite.isChecked)
            {
                var isExist: Boolean = false
                listCurrency[position].isFavorite = true
                isExist = listFavCurCode.contains(listCurrency[position].code)
                if (!isExist)
                {
                    listFavCurCode.add(listCurrency[position].code)
                }
                Toast.makeText(it.context, "Favorilendi", Toast.LENGTH_SHORT).show()
            }
            else
            {
                listCurrency[position].isFavorite = false
                var index: Int = 0
                index = listFavCurCode.indexOf(listCurrency[position].code)

                if(index == -1){
                    // there isn't item in listFavoriteCurrency
                }
                else
                {

                    listFavCurCode.removeAt(index)
                }


            }
        }
    }
    override fun getItemCount(): Int {
        return listCurrency.size
    }

    fun refreshData(newCurrency: List<CurrencyDetails>) {
        listCurrency.clear()
        listCurrency.addAll(newCurrency)
        notifyDataSetChanged()
    }

    fun getFavoriteCurrencyName(): List<String?> {
        return listFavCurCode
    }
}