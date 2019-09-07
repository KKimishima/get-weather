package com.github.kkimishima.getweather.application.service

import arrow.core.Either
import arrow.core.extensions.either.monadError.monadError
import arrow.core.fix
import com.github.kkimishima.getweather.application.repository.WeatherRepository
import com.github.kkimishima.getweather.domain.Region
import com.github.kkimishima.getweather.domain.Weather
import com.github.kkimishima.getweather.domain.WeatherList
import com.github.kkimishima.getweather.util.EitherData
import com.github.kkimishima.getweather.util.ErrorMessage
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class WeatherService(private val weatherRepository: WeatherRepository<Weather>) {
  fun getWeather(): EitherData<WeatherList> = runBlocking {
    val asyncList = Region.getAll().map {
      async { weatherRepository.getAsyncOne(it) }
    }
    val awaitList = asyncList.map { it.await() }
    Either.monadError<ErrorMessage>().binding {
      awaitList.map {
        it.bind()
      }.let { WeatherList(it) }
    }.fix()
  }
}