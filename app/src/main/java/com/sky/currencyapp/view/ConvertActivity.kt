package com.sky.currencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sky.currencyapp.R
import com.sky.currencyapp.viewmodel.CurrencyLatestViewModel
import kotlinx.android.synthetic.main.activity_convert.*
import kotlinx.android.synthetic.main.activity_currency_latest.*


class ConvertActivity : AppCompatActivity() {

    private lateinit var viewModel : CurrencyLatestViewModel
    var spinnerFrom: Spinner? = null
    var spinnerTo: Spinner? = null
    var list = ArrayList<String?>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)


        viewModel = ViewModelProvider(this).get(CurrencyLatestViewModel::class.java)
        viewModel.getData()
        observeLiveData()

        for(item in list){
            println(item)
        }
    }

    private fun observeLiveData(){
        viewModel.currencies.observe(this, Observer{ currencies->

            currencies?.let {
                for(item in it){
                    list.add(item.code)
                }
            }
        })
    }
    fun onConvertButtonClicked(view: View){
        Toast.makeText(this, "Eklenecek", Toast.LENGTH_SHORT).show()
    }

}