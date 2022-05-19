package com.sky.currencyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sky.currencyapp.model.Currency

class CurrencyViewModel: ViewModel() {

    /*private val api = CurrencyApiService()
   private val disposable = CompositeDisposable()*/

    val currencies = MutableLiveData<List<Currency>>()


    fun getData()
    {
        getDataOffline()
    }

    private fun getDataOffline()
    {
        val usd = Currency("American Dollar","usd", "2", arrayListOf())
        val btc = Currency("Bitcoin","btc", "5", arrayListOf())
        val bnb = Currency("Binance","bnb", ".", arrayListOf())
        val eth = Currency("Etherium","eth", "1", arrayListOf())
        val matic = Currency("Polygon","matic","3", arrayListOf())
        val listCurrency = arrayListOf<Currency>(usd, btc, bnb, eth, matic)

        currencies.value = listCurrency

    }
}