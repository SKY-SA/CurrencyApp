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
import com.sky.currencyapp.viewmodel.CurrencyLatestViewModel.Companion.globalList
import kotlinx.android.synthetic.main.activity_convert.*
import kotlinx.android.synthetic.main.activity_currencies.view.*


class ConvertActivity : AppCompatActivity() {

    private lateinit var viewModel : CurrencyLatestViewModel

    var list = ArrayList<String?>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)




        var fromCurrency: String? = ""
        var toCurrency: String? = ""

        list.clear()
        for(item in globalList){
            list.add(item.code!!.uppercase())
        }
        var arr = list.toTypedArray()

        if(convertActivity_fromSpinner != null && convertActivity_toSpinner != null){
            val adapter = ArrayAdapter(this, com.bumptech.glide.R.layout.support_simple_spinner_dropdown_item,arr)
            convertActivity_fromSpinner.adapter = adapter
            convertActivity_toSpinner.adapter = adapter

            convertActivity_fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                     fromCurrency = arr[p2]!!.lowercase()
                   // Toast.makeText(applicationContext, "Seçilen ${fromCurrency}", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(applicationContext, "Please Choose the currency", Toast.LENGTH_SHORT).show()
                }

            }
        }
        convertActivity_toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                toCurrency = arr[p2]!!.lowercase()
              //  Toast.makeText(applicationContext, "Seçilen ${toCurrency}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Please Choose the currency", Toast.LENGTH_SHORT).show()
            }

        }

    }


    fun onConvertButtonClicked(view: View){
        var amount: Double? = null
        if(!convertActivity_amountEditText.text.isNullOrEmpty()){
            amount = convertActivity_amountEditText.text.toString().toDouble()
            println("Amount tutarı ${amount}")
        }
        else{
            println("Tutar gelmedi")
        }
    }

}