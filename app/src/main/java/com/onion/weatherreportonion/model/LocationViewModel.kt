package com.onion.weatherreportonion.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {
    private val _locationResult = MutableLiveData<Result<Pair<String, String>>>()
    val locationResult: LiveData<Result<Pair<String, String>>> = _locationResult

    fun setLocationResult(latitude: String, longitude: String) {
        _locationResult.value = Result.success(Pair(latitude, longitude))
    }

    fun setLocationError(error: String) {
        _locationResult.value = Result.failure(Exception(error))
    }

    fun processIntentData(latitude: String, longitude: String) {
        val latStr = "%.2f".format(latitude)
        val lonStr = "%.2f".format(longitude)
        setLocationResult(latStr, lonStr)
    }
}