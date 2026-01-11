package com.onion.weatherreportonion.weatherapi

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherRetrofitImpl {

    companion object {
        private const val TAG = "WeatherRetrofit"
    }

    private val baseUrl = "https://api.open-meteo.com/"

    fun getRetrofitImpl(): WeatherAPI {
        // Добавляем логгирование для отладки
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(TAG, "OkHttp: $message")
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY // Для отладки
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()

                // Логируем URL перед отправкой
                Log.d(TAG, "Request URL: ${original.url}")
                Log.d(TAG, "Request Headers: ${original.headers}")

                val response = chain.proceed(original)

                // Логируем ответ
                Log.d(TAG, "Response Code: ${response.code}")
                Log.d(TAG, "Response Headers: ${response.headers}")

                response
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d(TAG, "Retrofit Base URL: ${retrofit.baseUrl()}")

        return retrofit.create(WeatherAPI::class.java)
    }





}