package com.sky.currencyapp.model

data class Currency(
    var currencyName : String?,
    var currencyCode : String?,
    var decimalUnit : String?,
    var listCountry : ArrayList<String>?

){
    var id: Int? = 0
    var isFavorite: Boolean? = false

}