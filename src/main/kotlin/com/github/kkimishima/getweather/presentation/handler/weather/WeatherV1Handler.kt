package com.github.kkimishima.getweather.presentation.handler.weather

import arrow.core.Either
import com.github.kkimishima.getweather.application.service.WeatherService
import com.github.kkimishima.getweather.presentation.handler.WeatherHandlerInterFace
import com.github.kkimishima.getweather.presentation.resources.WeatherListResources
import com.github.kkimishima.getweather.util.ServerData
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerResponse

@Component
class WeatherV1Handler(private val weatherService: WeatherService) : WeatherHandlerInterFace {
  override val weather: ServerData = {
    weatherService.getWeather().let {
      when (it) {
        is Either.Right -> {
          ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(WeatherListResources(it.b))
        }
        is Either.Left -> {
          ServerResponse
              .badRequest()
              .contentType(MediaType.APPLICATION_JSON)
              .body(it.a.value)
        }
      }
    }
  }
}
