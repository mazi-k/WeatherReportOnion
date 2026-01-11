package com.onion.weatherreportonion.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentWeatherReportBinding

class WeatherReportFragment: Fragment() {

    private lateinit var binding: FragmentWeatherReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_report, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWeatherReportBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.friendsFeedButton.setOnClickListener {
            addFragment(R.id.fragment_container, FriendsFeedFragment())
        }
        binding.arrowLeft.setOnClickListener {}
        binding.arrowRight.setOnClickListener {}
    }

    private fun addFragment(container: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }

}