package com.sky.currencyapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.sky.currencyapp.R
import com.sky.currencyapp.model.CurrencyLatest
import com.sky.currencyapp.view.CurrenciesActivity
import com.sky.currencyapp.view.CurrencyLatestActivity
import kotlinx.android.synthetic.main.latest_activity_recycler_row.view.*
import java.lang.NullPointerException

class CurrencyLatestRecyclerAdapter(private val listCurrency : ArrayList<CurrencyLatest>,private val context :Context): RecyclerView.Adapter<CurrencyLatestRecyclerAdapter.CurrencyVH>() {
    class CurrencyVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
    private var chosenCurrencyName: String? = ""


    private val activity : CurrencyLatestActivity = context as CurrencyLatestActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.latest_activity_recycler_row,parent,false)

        return CurrencyVH(view)
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.itemView.latestRecyclerRow_textView.text = listCurrency[position].fullName
        holder.itemView.latestRecyclerRow_radioButton.text = listCurrency[position].name!!.uppercase()

        holder.itemView.latestRecyclerRow_radioButton.setOnClickListener { view->
            chosenCurrencyName = listCurrency[position].name
                val intent = Intent(context, CurrenciesActivity::class.java).apply {
                    this.putExtra("nameBaseCurrency", chosenCurrencyName)
                }
            context.startActivity(intent)
            (context as CurrencyLatestActivity).finishMe()
        }
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
    fun getChosenBaseCurrencyName(holder: CurrencyVH) :String{

        chosenCurrencyName?.let {
            return it
        }
        return ""
    }
}