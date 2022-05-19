package com.sky.currencyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.currencyapp.R
import com.sky.currencyapp.model.CurrencyLatest
import kotlinx.android.synthetic.main.favorites_recycler_row.view.*
import kotlinx.android.synthetic.main.latest_activity_recycler_row.view.*

class FavoriteRecyclerAdapter(private val list: ArrayList<CurrencyLatest>) : RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteCrHolder>() {
    class FavoriteCrHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCrHolder {
         val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.favorites_recycler_row,parent,false)
        return FavoriteCrHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteCrHolder, position: Int) {
        holder.itemView.favoritesRecyclerRow_currencyNameTxt.text = list[position].fullName
        holder.itemView.favoriteRecyclerRow_currencyCodeTxt.text = list[position].code
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun refreshData(newList: List<CurrencyLatest> )
    {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}