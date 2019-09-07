package com.github.kkimishima.getweather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class GetWeatherApplication

fun main(args: Array<String>) {
  runApplication<GetWeatherApplication>(*args)
}




