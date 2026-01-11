package com.onion.weatherreportonion.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.ActivityMainBinding
import com.onion.weatherreportonion.model.LocationService
import com.onion.weatherreportonion.model.LocationViewModel
import com.onion.weatherreportonion.model.PermissionHelper
import com.onion.weatherreportonion.ui.home.WeatherReportFragment
import com.onion.weatherreportonion.ui.profile.MyProfileFragment
import com.onion.weatherreportonion.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity(){

    private val fragmentMap = fillFragments()
    private lateinit var binding: ActivityMainBinding
    val viewModel by lazy { ViewModelProvider(this)[LocationViewModel::class.java] }
    private lateinit var locationService: LocationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationService = LocationService(this)
        initBottomNavigationMenu()
        checkPermissionsAndGetLocation()
        startFragment(R.id.fragment_container, WeatherReportFragment())
    }

    private fun initBottomNavigationMenu() {
        binding.bottomNavigationMenu.setOnItemSelectedListener { item: MenuItem ->
            run {
                fragmentMap[item.itemId]?.let { addFragment(R.id.fragment_container, it) }
            }
            true
        }
    }

    private fun fillFragments(): Map<Int, Fragment> {
        val fragments: MutableMap<Int, Fragment> = HashMap()
        fragments[R.id.menu_item_home] = WeatherReportFragment()
        fragments[R.id.menu_item_settings] = SettingsFragment()
        fragments[R.id.menu_item_profile] = MyProfileFragment()
        return fragments
    }

    private fun startFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(container, fragment).commit()
    }

    private fun addFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun checkPermissionsAndGetLocation() {
        if (PermissionHelper.hasLocationPermission(this)) {
            getLocation()
        }
    }
    private fun getLocation() {
        locationService.getLastLocation(object : LocationService.LocationCallback {
            override fun onLocationReceived(latitude: String, longitude: String) {
                viewModel.setLocationResult(latitude, longitude)
                sendResultBack()
            }

            override fun onLocationError(errorMessage: String) {
                viewModel.setLocationError(errorMessage)
                sendResultBack()
            }
        })
    }
    private fun sendResultBack() {
        val coordString = intent.getStringExtra("coordinates")
        coordString?.let {
            parseAndSetCoordinates(it)
        }
    }

    private fun parseAndSetCoordinates(coordinates: String) {
        try {
            val parts = coordinates.split(",")
            if (parts.size == 2) {
                val latitude = parts[0].trim()
                val longitude = parts[1].trim()
                viewModel.processIntentData(latitude, longitude)
            }
        } catch (e: Exception) {
            viewModel.setLocationError("Неверный формат координат")
        }
    }

}