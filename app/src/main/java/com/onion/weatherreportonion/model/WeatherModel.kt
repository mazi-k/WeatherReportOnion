package com.onion.weatherreportonion.model

data class WeatherModel(
    val curTemperature: String,
    val windSpeed: String,
    val mornTemperature: String,
    val dayTemperature: String,
    val evenTemperature: String,
    val nightTemperature: String,
    val curHour:String
)
