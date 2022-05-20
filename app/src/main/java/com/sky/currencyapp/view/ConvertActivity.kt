package com.sky.currencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sky.currencyapp.R
import com.sky.currencyapp.viewmodel.ConvertViewModel
import com.sky.currencyapp.viewmodel.CurrencyLatestViewModel.Companion.globalList
import kotlinx.android.synthetic.main.activity_convert.*


class ConvertActivity : AppCompatActivity() {

    private lateinit var viewModel : ConvertViewModel

    var list = ArrayList<String?>()

    var fromCurrency: String? = ""
    var toCurrency: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        viewModel = ViewModelProvider(this).get(ConvertViewModel::class.java)

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

            viewModel = ViewModelProvider(this).get(ConvertViewModel::class.java)
            println("Activity Observe Methodu içerisinde ${fromCurrency} ve ${toCurrency}")
            viewModel.convert(fromCurrency,toCurrency, amount.toInt())
            observeLiveData()
        }
        else{
            Toast.makeText(applicationContext, "Değeri Giriniz Lütfen", Toast.LENGTH_SHORT).show()
        }
    }
    private fun observeLiveData(){
        viewModel.convertJson.observe(this,Observer{ convertJson->
            convertJson?.let { convertJsonIt->
                convertJsonIt.meta?.let { meta->
                    if(meta.code == 200){
                        convertJsonIt.convertResponse?.let {
                            Toast.makeText(applicationContext, "Code is ${meta.code}\n Amount Tutarı ${it.amount} \nValue  Tutarı = ${it.value}", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(applicationContext, "Hata Kodu ${meta.code}", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }
}