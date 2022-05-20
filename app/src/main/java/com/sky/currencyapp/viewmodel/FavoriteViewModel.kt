package com.sky.currencyapp.viewmodel

import androidx.lifecycle.*
import com.sky.currencyapp.model.CurrencyLatest
import com.sky.currencyapp.viewmodel.CurrencyLatestViewModel.Companion.globalList

class FavoriteViewModel : ViewModel() {


    val currencies = MutableLiveData<ArrayList<CurrencyLatest>>()
    private var listFav = ArrayList<CurrencyLatest>()
    fun getData(listFavName: ArrayList<String?>)
    {
        getDataOffline(listFavName)
    }

    private fun getDataOffline(listFavName: ArrayList<String?>)
    {
        for(currencyLatest in globalList)
        {
           for(code in listFavName){
                if(currencyLatest.code == code){
                    listFav.add(currencyLatest)
                }
            }
        }
        currencies.value = listFav
    }

}