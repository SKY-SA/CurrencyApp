package com.sky.currencyapp.viewmodel

import androidx.lifecycle.*
import com.sky.currencyapp.model.CurrencyLatest

class FavoriteViewModel : ViewModel() {

    private lateinit var ltCurrencyViewModel: CurrencyLatestViewModel
    val currencies = MutableLiveData<ArrayList<CurrencyLatest>>()

    private var listAllCurrencyLatest = ArrayList<CurrencyLatest>()
    private var listFav = ArrayList<CurrencyLatest>()

    fun getData(listFavName: ArrayList<String?>, owner: ViewModelStoreOwner, ownerLifecycleOwner: LifecycleOwner)
    {
        getDataOffline(listFavName,owner, ownerLifecycleOwner)
    }

    private fun getDataOffline(listFavName: ArrayList<String?>,owner: ViewModelStoreOwner, ownerLifecycleOwner: LifecycleOwner)
    {

       getDataFromViewModel(owner,ownerLifecycleOwner)
        // veritabanında bulunan bütün döviz ile favorileri karşılaştırıyoruz
        for(item in listFavName)
        {
            println("birinci for ${item}")
           for(currencyLatest in listAllCurrencyLatest){
               //println("ikinci for ${currencyLatest}")
                if(currencyLatest.code == item){
                    listFav.add(currencyLatest)
                    //println("\ncurrencyLatest adding = ${currencyLatest}")
                }
            }
        }
        currencies.value = listFav
    }
    private fun getDataFromViewModel(owner: ViewModelStoreOwner,ownerLifecycleOwner: LifecycleOwner)
    {
        ltCurrencyViewModel = ViewModelProvider(owner).get(CurrencyLatestViewModel::class.java)
        ltCurrencyViewModel.getData()
        observerLiveData(ownerLifecycleOwner)
        println("\nBir üst fonksiyonda")
        for(item in listAllCurrencyLatest){
            println(item)
        }
    }
    private fun observerLiveData(ownerLifecycleOwner: LifecycleOwner)
    {
        ltCurrencyViewModel.currencies.observe(ownerLifecycleOwner, Observer{ currencies->
           currencies?.let {
               listAllCurrencyLatest.addAll(it)
               println("observer içi")
               for(item in listAllCurrencyLatest){
                   println(item)
               }
           }
        })
    }

}