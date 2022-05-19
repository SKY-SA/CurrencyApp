package com.sky.currencyapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import com.sky.currencyapp.R

class WelcomeActivity : AppCompatActivity() {
    var numara = 0
    var runnable: Runnable = Runnable {}
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        runnable = Runnable {
            numara += 1
            handler.postDelayed(runnable,10)

            if(numara == 1){
                val intent = Intent(applicationContext,CurrencyLatestActivity::class.java)
                startActivity(intent)
                handler.removeCallbacks(runnable)
                finish()
            }
        }
        handler.post(runnable)

    }


}