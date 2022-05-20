package com.sky.currencyapp.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code")
    val code: Int?, // 200
    @SerializedName("disclaimer")
    val disclaimer: String? // Usage subject to terms: https://currencyscoop.com/terms
)