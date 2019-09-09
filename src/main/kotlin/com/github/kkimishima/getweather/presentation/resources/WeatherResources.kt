package com.github.kkimishima.getweather.presentation.resources

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.kkimishima.getweather.domain.Weather
import com.github.kkimishima.getweather.domain.WeatherList
import java.time.LocalDateTime

data class WeatherResources(
        val condition: String,
        val icon: String,
        val temp: Double,
        val temp_min: Double,
        val temp_max: Double,
        @field:JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "Asia/Tokyo")
        val time: LocalDateTime,
        val region: String
) {
    companion object {
        operator fun invoke(weather: Weather): WeatherResources {
            return WeatherResources(
                    condition = weather.condition.condition,
                    icon = weather.condition.icon,
                    temp = weather.temperature.temperature,
                    temp_min = weather.temperature.temperatureMin,
                    temp_max = weather.temperature.temperatureMax,
                    time = weather.measuringTime.time,
                    region = weather.regionName.regionName
            )
        }
    }
}

data class WeatherListResources(
        val weather: List<WeatherResources>
) {
    companion object {
        operator fun invoke(weatherList: WeatherList): WeatherListResources = weatherList.weather
                .map {
                    WeatherResources(it)
                }
                .let { WeatherListResources(it) }
    }
}
