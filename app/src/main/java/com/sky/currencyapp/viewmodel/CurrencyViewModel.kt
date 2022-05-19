package com.sky.currencyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sky.currencyapp.model.CurrencyDetails

class CurrencyViewModel: ViewModel() {

    /*private val api = CurrencyApiService()
   private val disposable = CompositeDisposable()*/

    val currencies = MutableLiveData<List<CurrencyDetails>>()


    fun getData()
    {
        getDataOffline()
    }

    private fun getDataOffline()
    {
        val usd = CurrencyDetails("American Dollar","usd", "2", arrayListOf())
        val btc = CurrencyDetails("Bitcoin","btc", "5", arrayListOf())
        val bnb = CurrencyDetails("Binance","bnb", ".", arrayListOf())
        val eth = CurrencyDetails("Etherium","eth", "1", arrayListOf())
        val matic = CurrencyDetails("Polygon","matic","3", arrayListOf())
        val listCurrency = arrayListOf<CurrencyDetails>(usd, btc, bnb, eth, matic)

        currencies.value = listCurrency

    }
}