package com.github.kkimishima.getweather.presentation

import com.github.kkimishima.getweather.presentation.handler.WeatherHandlerInterFace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@Configuration
class Router(private val weatherV1Handler: WeatherHandlerInterFace) {
  @Bean
  fun routes(): RouterFunction<ServerResponse> = router {
    "/api".nest {
      "/v1".nest {
        GET("/weather", weatherV1Handler.weather)
      }
    }
  }
}