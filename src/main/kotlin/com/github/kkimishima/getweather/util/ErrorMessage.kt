package com.github.kkimishima.getweather.util

sealed class ErrorMessage {
  abstract val value: String

  object NetworkError : ErrorMessage() {
    override val value: String = """
       {"error message":"ネットワーク通信失敗: 管理者に問い合わせてください。" }
    """.trimIndent()
  }
}