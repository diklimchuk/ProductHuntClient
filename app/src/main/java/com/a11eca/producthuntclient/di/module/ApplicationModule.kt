package com.a11eca.producthuntclient.di.module

import android.app.Application
import com.a11eca.producthuntclient.BuildConfig
import com.a11eca.producthuntclient.data.api.interceptor.AuthInterceptor
import com.a11eca.producthuntclient.data.api.service.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule constructor(
    private val application: Application
) {

  private fun client(): OkHttpClient {
    val authInterceptor = AuthInterceptor()
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
  }

  @Provides
  @Singleton
  fun retrofit(): Retrofit {
    return Retrofit.Builder()
        .client(client())
        .baseUrl(BuildConfig.REST_HOST)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides
  @Singleton
  fun apiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }
}