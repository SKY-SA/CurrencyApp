package com.sky.currencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.currencyapp.R
import com.sky.currencyapp.adapter.CurrenciesRecyclerAdapter
import com.sky.currencyapp.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_currencies.*

class CurrenciesActivity : AppCompatActivity() {

    private lateinit var viewModel: CurrencyViewModel
    private val recyclerAdapter = CurrenciesRecyclerAdapter(arrayListOf(),"")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencies)


        viewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        viewModel.getData()

        observeLiveData()

        currenciesActivity_recyclerView.layoutManager = LinearLayoutManager(this)
        currenciesActivity_recyclerView.adapter = recyclerAdapter
    }
    private fun observeLiveData(){
        viewModel.currencies.observe(this, Observer{ currency->

            currency?.let {
                currenciesActivity_recyclerView.visibility = View.VISIBLE
                recyclerAdapter.refreshData(it)
            }
        })
    }
    fun onCheckBoxClicked(view: View){

    }
}