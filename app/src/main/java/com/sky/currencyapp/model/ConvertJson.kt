package com.sky.currencyapp.model


import com.google.gson.annotations.SerializedName

data class ConvertJson(
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("response")
    val convertResponse: ConvertResponse?
)