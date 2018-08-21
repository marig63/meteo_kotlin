package com.marisa.guillaume.appmeteo.city

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marisa.guillaume.appmeteo.App
import com.marisa.guillaume.appmeteo.R
import com.marisa.guillaume.appmeteo.weather.WeatherActivity
import com.marisa.guillaume.appmeteo.weather.WeatherFragment

class MainActivity : AppCompatActivity(), CityFragment.CityFragmentListener {

    private lateinit var cityFragment: CityFragment
    private var currentCity : City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        cityFragment = supportFragmentManager.findFragmentById(R.id.city_fragment ) as CityFragment
        cityFragment.listener = this
    }

    override fun onCitySelected(city: City) {
        currentCity = city
        startWeatherActivity(city)
    }

    private fun startWeatherActivity(city: City) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(WeatherFragment.EXTRA_CITY_NAME,city.name)
        startActivity(intent)
    }

}
