package com.github.kkimishima.getweather.domain

import java.time.LocalDateTime
import java.time.ZoneOffset

data class WeatherList(
    val weather: List<Weather>
)

data class Weather(
    val condition: Condition,
    val temperature: Temperature,
    val measuringTime: MeasuringTime,
    val regionName: RegionName
) {
  companion object {
    operator fun invoke(
        conditionId: Int,
        icon: String,
        temperature: Double,
        temperatureMax: Double,
        temperatureMin: Double,
        dt: Long,
        region: Region
    ): Weather {
      return Weather(
          condition = Condition(conditionId, icon),
          temperature = Temperature(temperature, temperatureMax, temperatureMin),
          measuringTime = MeasuringTime(dt),
          regionName = region.name
      )
    }
  }
}

data class Condition(
    val condition: String,
    val icon: String
) {
  companion object {
    operator fun invoke(conditionId: Int, icon: String): Condition {
      return Condition(
          condition = jpDescription(conditionId),
          icon = "http://openweathermap.org/img/wn/${icon}@2x.png"
      )
    }

    fun jpDescription(id: Int): String {
      return when (id) {
        in 200..299 -> return "雷雨"
        in 300..399 -> return "霧雨"
        in 500..599 -> return "雨"
        in 600..699 -> return "雪"
        in 700..799 -> return "霧"
        800 -> return "晴天"
        in 801..899 -> return "曇り"
        else -> "未設定"
      }
    }
  }
}

data class Temperature(
    val temperature: Double,
    val temperatureMax: Double,
    val temperatureMin: Double
)

data class MeasuringTime(
    val time: LocalDateTime
) {
  companion object {
    operator fun invoke(dt: Long): MeasuringTime = LocalDateTime
        .ofEpochSecond(dt, 0, ZoneOffset.of("+09:00"))
        .let { MeasuringTime(it) }
  }
}
