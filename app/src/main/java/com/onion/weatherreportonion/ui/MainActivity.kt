package com.onion.weatherreportonion.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.ActivityMainBinding
import com.onion.weatherreportonion.ui.home.WeatherReportFragment
import com.onion.weatherreportonion.ui.profile.MyProfileFragment
import com.onion.weatherreportonion.ui.settings.SettingsFragment
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private val fragmentMap = fillFragments()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationMenu()

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
}