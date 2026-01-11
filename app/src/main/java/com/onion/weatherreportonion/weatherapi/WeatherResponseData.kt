package com.onion.weatherreportonion.weatherapi
import com.google.gson.annotations.SerializedName


data class WeatherResponseData (
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null,
    @SerializedName("generationtime_ms")
    var generationtimeMs: Double? = null,
    @SerializedName("utc_offset_seconds")
    var utcOffsetSeconds: Int? = null,
    @SerializedName("timezone")
    var timezone: String? = null,
    @SerializedName("timezone_abbreviation")
    var timezoneAbbreviation : String? = null,
    @SerializedName("elevation")
    var elevation: Int? = null,
    @SerializedName("current_units")
    var currentUnits: CurrentUnits? = CurrentUnits(),
    @SerializedName("current")
    var current: Current? = Current(),
    @SerializedName("hourly_units")
    var hourlyUnits: HourlyUnits? = HourlyUnits(),
    @SerializedName("hourly")
    var hourly: Hourly? = Hourly()
)

data class HourlyUnits (
    @SerializedName("time")
    var time: String? = null,
    @SerializedName("temperature_2m")
    var temperature2m: String? = null
)

data class Hourly (
    @SerializedName("time")
    var time: ArrayList<String> = arrayListOf(),
    @SerializedName("temperature_2m")
    var temperature2m: ArrayList<Double> = arrayListOf()
)

data class CurrentUnits (
    @SerializedName("time")
    var time: String? = null,
    @SerializedName("interval")
    var interval: String? = null,
    @SerializedName("wind_speed_10m")
    var windSpeed10m: String? = null,
    @SerializedName("temperature_2m")
    var temperature2m: String? = null
)

data class Current (
    @SerializedName("time")
    var time: String? = null,
    @SerializedName("interval")
    var interval: Int? = null,
    @SerializedName("wind_speed_10m")
    var windSpeed10m: Double? = null,
    @SerializedName("temperature_2m")
    var temperature2m: Double? = null
)

