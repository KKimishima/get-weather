package com.github.kkimishima.getweather.infrastructure.transfer

data class WeatherMapper(
    val coord: WeatherMapperDataClass.Coord,
    val weather: List<WeatherMapperDataClass.Weather>,
    val base: String,
    val main: WeatherMapperDataClass.Main,
    val visibility: Int,
    val wind: WeatherMapperDataClass.Wind,
    val clouds: WeatherMapperDataClass.Clouds,
    val dt: Long,
    val sys: WeatherMapperDataClass.Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Int
)

object WeatherMapperDataClass {
  // 座標
  data class Coord(
      val lon: Double,
      val lat: Double
  )

  // 天気
  data class Weather(
      val id: Int,
      val main: String,
      val description: String,
      val icon: String
  )

  // 気温
  data class Main(
      val temp: Double,
      val pressure: Int, // 圧力
      val humidity: Int, //湿度
      val temp_min: Double,
      val temp_max: Double
  )

  data class Wind(
      val speed: Double,
      // 報告
      val deg: Int
  )

  data class Clouds(
      val all: Double
  )

  data class Sys(
      val type: Int,
      val id: Int,
      val message: Double,
      val country: String,
      // 日の出
      val sunrise: Long,
      // 日の入り
      val sunset: Long
  )
}
