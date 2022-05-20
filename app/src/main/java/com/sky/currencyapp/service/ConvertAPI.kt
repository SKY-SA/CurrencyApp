package com.sky.currencyapp.service

import com.sky.currencyapp.model.ConvertJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConvertAPI
{
// url -> https://api.currencyscoop.com/v1/convert?api_key=adc14bb51f8f066a379d33b02ae1e4f7&from=USD&to=INR&amount=100

    @GET("convert?api_key=adc14bb51f8f066a379d33b02ae1e4f7")
    fun convert(@Query("from") fromCurrency: String?,
                  @Query("to") toCurrency: String?,
                  @Query("amount") amount:Int?,
    ) : Single<ConvertJson>


}