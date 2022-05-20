package com.sky.currencyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sky.currencyapp.model.ConvertJson
import com.sky.currencyapp.service.ConvertAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ConvertViewModel : ViewModel() {

    private val api = ConvertAPIService()
    private val disposable = CompositeDisposable()

    val convertJson = MutableLiveData<ConvertJson>()

    fun convert(fromCurrency:String?, toCurrency:String?, amount: Int?)
    {
        convertFromInternet(fromCurrency,toCurrency,amount)
    }
    private fun convertFromInternet(fromCurrency:String?, toCurrency:String?, amount: Int?){
        println("ViewModel içerisi ${fromCurrency} ve ${fromCurrency!!.uppercase()}")
        disposable.add(
            api.getConvertResult(fromCurrency!!.uppercase(),toCurrency!!.uppercase(),amount)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ConvertJson>(){
                    override fun onSuccess(t: ConvertJson) {
                        convertJson.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}