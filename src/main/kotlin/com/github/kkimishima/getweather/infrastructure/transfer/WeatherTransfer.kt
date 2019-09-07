package com.github.kkimishima.getweather.infrastructure.transfer

import arrow.core.Either
import com.github.kkimishima.getweather.application.repository.WeatherRepository
import com.github.kkimishima.getweather.domain.Region
import com.github.kkimishima.getweather.domain.Weather
import com.github.kkimishima.getweather.util.EitherData
import com.github.kkimishima.getweather.util.ErrorMessage
import com.github.kkimishima.getweather.util.OpenWeatherMapConfBean
import com.github.kkimishima.getweather.util.RestTemplateBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Repository
import org.springframework.web.util.UriComponentsBuilder


@Repository
class WeatherTransfer(
    private val openWeatherMapConfBean: OpenWeatherMapConfBean,
    private val restTemplateBean: RestTemplateBean
) : WeatherRepository<Weather> {
  override suspend fun getAsyncOne(region: Region): EitherData<Weather> = withContext(Dispatchers.IO) {
    getOne(region)
  }

  override fun getOne(region: Region): EitherData<Weather> {
    val builder = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfBean.url)
        .queryParam("units", openWeatherMapConfBean.units)
        .queryParam("appid", openWeatherMapConfBean.appid)
        .queryParam("q", region.value)
    return kotlin.runCatching {
      restTemplateBean.restTemplate().exchange(
          builder.build().encode().toUri(),
          HttpMethod.GET,
          null,
          WeatherMapper::class.java
      ).body.let {
        if (it == null) {
          Either.left(ErrorMessage.NetworkError)
        } else {
          Either.right(
              Weather(
                  conditionId = it.weather.first().id,
                  icon = it.weather.first().icon,
                  temperature = it.main.temp,
                  temperatureMax = it.main.temp_max,
                  temperatureMin = it.main.temp_min,
                  dt = it.dt,
                  region = region
              )
          )
        }
      }
    }.getOrElse {
      Either.left(ErrorMessage.NetworkError)
    }
  }
}