package com.sky.currencyapp.model


import com.google.gson.annotations.SerializedName

data class ConvertResponse(
    @SerializedName("amount")
    val amount: Int?, // 100
    @SerializedName("date")
    val date: String?, // 2022-05-20
    @SerializedName("from")
    val from: String?, // USD
    @SerializedName("timestamp")
    val timestamp: Int?, // 1653047003
    @SerializedName("to")
    val to: String?, // INR
    @SerializedName("value")
    val value: Double? // 7763.3324299999995
)