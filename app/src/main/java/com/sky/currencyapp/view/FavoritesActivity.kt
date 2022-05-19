package com.sky.currencyapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.sky.currencyapp.R

private var listFavoriteCurrencyNane = ArrayList<String?>()
class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)


        intent?.let {
           listFavoriteCurrencyNane =  it.getStringArrayListExtra("list")!!
            listFavoriteCurrencyNane.add(it.getStringExtra("baseCurrency"))
        }

        for(p in listFavoriteCurrencyNane){
            println("Favorites içerisi ${p}")
        }
    }

}