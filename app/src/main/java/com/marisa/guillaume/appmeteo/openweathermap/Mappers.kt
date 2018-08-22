package com.marisa.guillaume.appmeteo.openweathermap

import com.marisa.guillaume.appmeteo.weather.Weather

fun mapOpenWeatherDataToWeather(weatherWrapper: WeatherWrapper): Weather {

    val weatherFirst = weatherWrapper.weather.first()
    return Weather(
            description = weatherFirst.description,
            temperature = weatherWrapper.main.temperature,
            humidity = weatherWrapper.main.humidity,
            presure = weatherWrapper.main.pressure,
            iconUrl = "https://openweathermap.org/img/w/${weatherFirst.icon}.png"
    )

}