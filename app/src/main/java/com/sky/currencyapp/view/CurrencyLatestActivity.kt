package com.sky.currencyapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.currencyapp.R
import com.sky.currencyapp.adapter.CurrencyLatestRecyclerAdapter
import com.sky.currencyapp.model.CurrencyLatest
import com.sky.currencyapp.viewmodel.CurrencyLatestViewModel
import kotlinx.android.synthetic.main.activity_currency_latest.*

class CurrencyLatestActivity : AppCompatActivity() {

    private val recyclerAdapter = CurrencyLatestRecyclerAdapter(arrayListOf(), this)
    private lateinit var viewModel: CurrencyLatestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_latest)
        viewModel = ViewModelProvider(this).get(CurrencyLatestViewModel::class.java)

        viewModel.getData()
        observeLiveData()

        currencyLatestActivity_recyclerView.layoutManager = LinearLayoutManager(this)
        currencyLatestActivity_recyclerView.adapter = recyclerAdapter



    }

    private fun observeLiveData()
    {
        viewModel.currencies.observe(this, Observer{ currency->

            currency?.let {
                currencyLatestActivity_recyclerView.visibility = View.VISIBLE
                recyclerAdapter.refreshData(it)
            }
        })
        viewModel.isLoading.observe(this, Observer{ loading->
            loading?.let {
                if(it){
                    currencyLatestActivity_recyclerView.visibility = View.GONE
                    currencyLatestActivity_errorTextView.visibility = View.GONE
                    currencyLatestActivity_progressBar.visibility = View.VISIBLE
                }
                else{
                    currencyLatestActivity_recyclerView.visibility = View.VISIBLE
                    currencyLatestActivity_progressBar.visibility = View.GONE
                }
            }
        })
        viewModel.isSuccess.observe(this, Observer{ success->
            success?.let {
                if(it)
                {
                    currencyLatestActivity_errorTextView.visibility = View.GONE
                    currencyLatestActivity_recyclerView.visibility = View.VISIBLE
                }
                else{
                    currencyLatestActivity_errorTextView.visibility = View.VISIBLE
                    currencyLatestActivity_recyclerView.visibility = View.GONE
                }
            }
        })
    }
    public fun finishMe()
    {
        finish()
    }
}