package com.sky.currencyapp.model

class CurrencyDetails(
    val name: String?,
    val code : String?,
    val decimalUnits: String?,
    val listCountry : ArrayList<String>?
) {
    var id: Int? = 0
    var isFavorite: Boolean? = false
}