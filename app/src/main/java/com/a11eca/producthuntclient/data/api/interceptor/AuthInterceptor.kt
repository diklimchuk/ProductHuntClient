package com.a11eca.producthuntclient.data.api.interceptor

import com.a11eca.producthuntclient.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

/**
 * [Interceptor] that is responsible for authentication in api.
 */
class AuthInterceptor : Interceptor {

  companion object {
    const val ACCESS_TOKEN_HEADER = "access_token"
  }

  /**
   * Adds access_token to every request.
   */
  override fun intercept(chain: Chain): Response {
    val request = chain.request()
    val url = request.url()
        .newBuilder()
        .addQueryParameter(ACCESS_TOKEN_HEADER, BuildConfig.ACCESS_TOKEN)
        .build()
    val newRequest = request.newBuilder().url(url).build()
    return chain.proceed(newRequest)
  }
}