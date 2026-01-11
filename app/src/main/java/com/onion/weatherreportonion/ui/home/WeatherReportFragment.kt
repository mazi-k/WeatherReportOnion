package com.onion.weatherreportonion.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentWeatherReportBinding
import com.onion.weatherreportonion.model.LocationViewModel
import com.onion.weatherreportonion.model.WeatherModel
import com.onion.weatherreportonion.weatherapi.WeatherResponseData
import com.onion.weatherreportonion.weatherapi.WeatherRetrofitImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherReportFragment: Fragment() {

    private lateinit var binding: FragmentWeatherReportBinding
    private lateinit var retrofitImpl: WeatherRetrofitImpl

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[LocationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LocationFragment", "onCreateView:")
        return inflater.inflate(R.layout.fragment_weather_report, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWeatherReportBinding.bind(view)
        retrofitImpl = WeatherRetrofitImpl()

        initElements()
        observeViewModel()
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

    private fun observeViewModel() {
        // Наблюдаем за основным LiveData с результатом
        viewModel.locationResult.observe(viewLifecycleOwner) { result ->
            result?.let {
                handleLocationResult(it)
            }
        }
    }

    private fun handleLocationResult(result: Result<Pair<String, String>>) {
        result.fold(
            onSuccess = { (latitude, longitude) ->
                getWeatherInfo(latitude, longitude)
                Log.d("LocationFragment", "Координаты: $latitude, $longitude")
            },
            onFailure = {
                binding.weatherSubText.text =  "Неизвестная ошибка"
            }
        )

    }

    @SuppressLint("SetTextI18n")
    private fun getWeatherInfo(latitude:String, longitude:String) {
        val latitudeSubst = latitude.substring(0, 5).replace(",", ".")
        val longitudeSubst = longitude.substring(0, 5).replace(",", ".")

        CoroutineScope(Dispatchers.IO).launch {

            val result = retrofitImpl.getRetrofitImpl().getWeather(
                latitudeSubst, longitudeSubst)

            if (result.isSuccessful) {
                val weather = getWeatherByResponse(result)
                Log.i("LocationFragment", "curHour" + weather.curHour.toInt().toString())

                withContext(Dispatchers.Main) {
                    binding.weatherTextView.text = "Сейчас: " + weather.curTemperature + "°C, скорость ветра: " + weather.windSpeed + "м/с"

                    if (weather.curHour.toInt() < 6){
                        binding.weatherSubText.text =
                            """Утром: ${weather.mornTemperature}°C,
                                |Днем: ${weather.dayTemperature}°C,
                                |Вечером: ${weather.evenTemperature}°C,
                                |Ночью: ${weather.evenTemperature}°C""".trimMargin()
                    } else if (weather.curHour.toInt() < 12) {
                        binding.weatherSubText.text =
                            """Днем: ${weather.dayTemperature}°C,
                                |Вечером: ${weather.evenTemperature}°C,
                                |Ночью: ${weather.evenTemperature}°C""".trimMargin()
                    } else if (weather.curHour.toInt() < 18) {
                        binding.weatherSubText.text =
                            """Вечером: ${weather.evenTemperature}°C,
                                |Ночью: ${weather.evenTemperature}°C""".trimMargin()
                    } else {
                        binding.weatherSubText.text =
                            """Ночью: ${weather.evenTemperature}°C""".trimMargin()
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Log.e("LocationFragment", result.errorBody().toString())
                    binding.weatherTextView.text = "Неизвестная ошибка"
                    binding.weatherSubText.text = "Неизвестная ошибка"
                }
            }
        }
    }

    private fun getWeatherByResponse(result: Response<WeatherResponseData>): WeatherModel {
        val weather = WeatherModel(
            curTemperature = result.body()?.current?.temperature2m.toString(),
            windSpeed = result.body()?.current?.windSpeed10m.toString(),
            mornTemperature = result.body()?.hourly?.temperature2m?.get(6).toString(),
            dayTemperature = result.body()?.hourly?.temperature2m?.get(12).toString(),
            evenTemperature = result.body()?.hourly?.temperature2m?.get(18).toString(),
            nightTemperature = result.body()?.hourly?.temperature2m?.get(24).toString(),
            curHour=formatTime(result.body()?.current?.time.toString())
        )
        return weather
    }

    private fun formatTime(isoTime: String): String {
        return try {
            isoTime.substring(11, 13)
        } catch (e: Exception) {
            "no"
        }
    }




}