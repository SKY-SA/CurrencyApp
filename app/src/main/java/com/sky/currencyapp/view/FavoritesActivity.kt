package com.sky.currencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.currencyapp.R
import com.sky.currencyapp.adapter.FavoriteRecyclerAdapter
import com.sky.currencyapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorites.*

private var listFavoriteCurrencyNane = ArrayList<String?>()

private lateinit var viewModel : FavoriteViewModel
val recyclerAdapter = FavoriteRecyclerAdapter(arrayListOf())


class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)


        intent?.let {
            listFavoriteCurrencyNane =  it.getStringArrayListExtra("list")!!
            listFavoriteCurrencyNane.add(it.getStringExtra("baseCurrency"))

        }

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.getData(listFavoriteCurrencyNane,this,this)

        observeLiveData()

        favoritesActivity_recyclerView.layoutManager = LinearLayoutManager(this)
        favoritesActivity_recyclerView.adapter = recyclerAdapter
    }

    private fun observeLiveData()
    {
        viewModel.currencies.observe(this, Observer {
            it?.let {
                favoritesActivity_recyclerView.visibility = View.VISIBLE
                recyclerAdapter.refreshData(it)
            }
        })
    }
    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}