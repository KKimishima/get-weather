package com.github.kkimishima.getweather.util

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("openweathermap")
data class OpenWeatherMapConfBean(
    val units: String,
    val appid: String,
    val url: String
)
