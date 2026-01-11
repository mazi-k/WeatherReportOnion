package com.onion.weatherreportonion.weatherapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// v1/forecast?latitude=55.45&longitude=37.37&hourly=temperature_2m&current=wind_speed_10m,temperature_2m

interface WeatherAPI {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("current") current: String = "wind_speed_10m,temperature_2m",
        @Query("timezone") timezone: String = "auto"
    ): Response<WeatherResponseData>


//    @GET("v1/forecast")
//    suspend fun getDefaultWeather(
//        @Query("latitude") latitude: Double,
//        @Query("longitude") longitude: Double
//    ): Response<WeatherResponseData>
}