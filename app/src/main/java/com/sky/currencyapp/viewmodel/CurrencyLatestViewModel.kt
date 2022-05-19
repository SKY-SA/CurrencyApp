package com.sky.currencyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sky.currencyapp.model.CurrencyLatest

class CurrencyLatestViewModel : ViewModel() {

    /*private val api = CurrencyApiService()
    private val disposable = CompositeDisposable()*/

    val currencies = MutableLiveData<List<CurrencyLatest>>()
    val isLoading = MutableLiveData<Boolean>()
    val isSuccess = MutableLiveData<Boolean>()

    fun getData()
    {
        getDataOffline()
    }

    private fun getDataOffline()
    {

        val usd = CurrencyLatest("American Dollar","usd", 15.2)
        val btc = CurrencyLatest("Bitcoin","btc", 29000.1)
        val bnb = CurrencyLatest("Binance","bnb", 302.3)
        val eth = CurrencyLatest("Etherium","eth", 2500.8)
        val matic = CurrencyLatest("Polygon","matic", 0.5)
        val listCurrency = arrayListOf<CurrencyLatest>(usd, btc, bnb, eth, matic)

        currencies.value = listCurrency
        isLoading.value = false
        isSuccess.value = true
    }
}