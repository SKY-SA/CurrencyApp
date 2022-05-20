package com.sky.currencyapp.service

import com.sky.currencyapp.model.ConvertJson
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConvertAPIService {
    // url -> https://api.currencyscoop.com/v1/convert?api_key=adc14bb51f8f066a379d33b02ae1e4f7&from=USD&to=INR&amount=100
    private val BASE_URL = "https://api.currencyscoop.com/v1/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ConvertAPI::class.java)

    fun getConvertResult(fromCurrency:String?, toCurrency:String?, amount: Int?) : Single<ConvertJson>
    {
        return api.convert(fromCurrency,toCurrency,amount)
    }

}