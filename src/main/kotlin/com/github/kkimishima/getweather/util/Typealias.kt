package com.github.kkimishima.getweather.util

import arrow.core.Either
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

typealias ServerData = (ServerRequest) -> ServerResponse
typealias EitherData<T> = Either<ErrorMessage, T>
