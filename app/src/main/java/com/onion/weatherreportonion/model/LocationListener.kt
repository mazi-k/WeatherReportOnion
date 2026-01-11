package com.onion.weatherreportonion.model

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.*

class LocationService(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    interface LocationCallback {
        fun onLocationReceived(latitude: String, longitude: String)
        fun onLocationError(errorMessage: String)
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation(callback: LocationCallback) {
        if (!PermissionHelper.hasLocationPermission(context)) {
            callback.onLocationError("Нет разрешения на доступ к местоположению")
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = "%.6f".format(location.latitude)
                    val longitude = "%.6f".format(location.longitude)
                    callback.onLocationReceived(latitude, longitude)
                } else {
                    callback.onLocationError("Местоположение не найдено")
                }
            }
            .addOnFailureListener { exception ->
                callback.onLocationError("Ошибка: ${exception.message}")
            }
    }
}

object PermissionHelper {
    fun hasLocationPermission(context: Context): Boolean {
        return androidx.core.content.ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED ||
                androidx.core.content.ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}