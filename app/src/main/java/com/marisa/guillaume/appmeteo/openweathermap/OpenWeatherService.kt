package com.marisa.guillaume.appmeteo.openweathermap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "a851db271d506c499fe1284d22e3cd75"

interface OpenWeatherService{

    @GET("data/2.5/weather?units=metric&lang=fr")
    fun getWeather(@Query("q") cityName: String,
                   @Query("appid") apiKey: String = API_KEY): Call<WeatherWrapper>
}