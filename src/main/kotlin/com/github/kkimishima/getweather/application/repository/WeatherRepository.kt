package com.github.kkimishima.getweather.application.repository

import com.github.kkimishima.getweather.domain.Region
import com.github.kkimishima.getweather.util.EitherData

interface WeatherRepository<T> {
  fun getOne(region: Region): EitherData<T>

  suspend fun getAsyncOne(region: Region): EitherData<T>
}