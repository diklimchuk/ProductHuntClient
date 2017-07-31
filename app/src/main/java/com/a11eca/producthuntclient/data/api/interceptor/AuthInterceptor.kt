package com.a11eca.producthuntclient.data.api.interceptor

import com.a11eca.producthuntclient.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor : Interceptor {

  companion object {
    const val ACCESS_TOKEN_HEADER = "acсess_token"
  }

  override fun intercept(chain: Chain): Response {
    val requestBuilder = chain.request().newBuilder()

    val request = requestBuilder.header(ACCESS_TOKEN_HEADER, BuildConfig.ACCESS_TOKEN).build()

    return chain.proceed(request)
  }
}