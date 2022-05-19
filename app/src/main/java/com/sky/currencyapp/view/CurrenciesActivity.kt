package com.sky.currencyapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.currencyapp.R
import com.sky.currencyapp.adapter.CurrenciesRecyclerAdapter
import com.sky.currencyapp.model.CurrencyDetails
import com.sky.currencyapp.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_currencies.*
import kotlinx.android.synthetic.main.currencies_activity_recycler_row.*
import kotlinx.android.synthetic.main.currencies_activity_recycler_row.view.*

class CurrenciesActivity : AppCompatActivity() {

    private lateinit var viewModel: CurrencyViewModel
    private val recyclerAdapter = CurrenciesRecyclerAdapter(arrayListOf())
    private var baseCurrencyName: String? = ""
    private lateinit var baseCurrency: CurrencyDetails
    private val listCurrency = ArrayList<CurrencyDetails>()
    private val listFavoriteCurrencyName = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencies)

        baseCurrencyName = intent.getStringExtra("nameBaseCurrency")


        viewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        viewModel.getData()

        observeLiveData()

        currenciesActivity_recyclerView.layoutManager = LinearLayoutManager(this)
        currenciesActivity_recyclerView.adapter = recyclerAdapter
    }

    private fun observeLiveData() {
        var index = 0
        viewModel.currencies.observe(this, Observer { currency ->

            currency?.let {
                currenciesActivity_recyclerView.visibility = View.VISIBLE

                for (currency in it) {
                    println("base currencyName is ${baseCurrencyName} and item name ${currency.code}")
                    if (baseCurrencyName == currency.code) {
                        baseCurrency = currency
                        break
                    }
                    index++
                }


                listCurrency.addAll(it)
                listCurrency.removeAt(index)
                currenciesActivity_currencyNameTextView.text = baseCurrency.name
                currenciesActivity_currencyCodeTextView.text = baseCurrency.code
                currenciesActivity_decimalUnitTextView.text = baseCurrency.decimalUnits

                recyclerAdapter.refreshData(listCurrency)
            }
        })
    }

    fun onCheckBoxClicked(view: View) {

        if (currenciesActivity_checkboxFavorite.isChecked) {
            baseCurrency.let {
                it.isFavorite = true
                Toast.makeText(this, "Base Currency favorilere eklendi", Toast.LENGTH_SHORT).show()
            }
        }  else{
            baseCurrency.let {
                it.isFavorite = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.currencies_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent()
        when(item.itemId){
            R.id.currenciesMenu_favorite ->{
            }
            R.id.currenciesMenu_convert ->{

            }
        }

        return super.onOptionsItemSelected(item)
    }
}