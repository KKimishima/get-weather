package com.github.kkimishima.getweather.util

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@Configuration
class RestTemplateBean {
  @Bean
  fun restTemplate(): RestTemplate = RestTemplateBuilder().build()
}

