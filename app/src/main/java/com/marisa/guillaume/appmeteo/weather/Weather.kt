package com.marisa.guillaume.appmeteo.weather

data class Weather(val description: String,
                   val temperature: Float,
                   val humidity: Int,
                   val presure: Int,
                   val iconUrl: String)
