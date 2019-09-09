package com.github.kkimishima.getweather.domain

import kotlin.reflect.full.isSubclassOf

data class RegionName(val regionName: String)
sealed class Region {
  abstract val value: String
  abstract val name: RegionName

  object IbarakiFactory : Region() {
    override val value: String = "Koga"
    override val name: RegionName = RegionName("茨城工場")
  }

  object TokyoOffice : Region() {
    override val value: String = "Tokyo"
    override val name: RegionName = RegionName("本社")
  }

  object ShirakawaFactory : Region() {
    override val value: String = "Sukagawa"
    override val name: RegionName = RegionName("白河工場")
  }

  object OsakaOffice : Region() {
    override val value: String = "Osaka-shi"
    override val name: RegionName = RegionName("大阪営業所")
  }

  object OsakaFactory : Region() {
    override val value: String = "Toyonaka"
    override val name: RegionName = RegionName("大阪工場")
  }

  companion object {
    fun getAll() = Region::class.nestedClasses
        .filter { it.isFinal && it.isSubclassOf(Region::class) }
        .map { it.objectInstance as Region }
        .toSet()
  }
}