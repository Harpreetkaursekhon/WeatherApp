package com.cloud.myweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (intent.getStringExtra("name").toString()!! != null) {

            val name: String = intent.getStringExtra("name")!!
            tv_suburb.text = "$name "
        }
//
//        if (intent.getStringExtra("wethertype").toString()!! != null) {
//            val wethertype: String = intent.getStringExtra("wethertype")!!
//            tv_weather_type.text = "$wethertype"
//        }

        if (intent.getStringExtra("temp").toString()!! != null) {
            val temp: String = intent.getStringExtra("temp")!!
            tv_degree.text = "$temp° "
        }

        if (intent.getStringExtra("feels").toString()!! != null) {
            val feels: String = intent.getStringExtra("feels")!!
            tv_feel.text = "$feels° "
        }

        if (intent.getStringExtra("humidity").toString()!! != null) {
            var humidity: String = intent.getStringExtra("humidity")!!
            humidity=humidity.replace("Humidity:","")
            tv_humidity.text = "$humidity "
        }

        if (intent.getStringExtra("wind").toString()!! != null) {
            var wind: String = intent.getStringExtra("wind")!!
            wind=wind.replace("Wind:","")
            tv_wind.text = "$wind "
        }

        ic_close.setOnClickListener {
            finish();
        }

    }
}