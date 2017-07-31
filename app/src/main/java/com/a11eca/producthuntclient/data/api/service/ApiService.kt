package com.a11eca.producthuntclient.data.api.service

import com.a11eca.producthuntclient.data.api.json.JsonCategories
import com.a11eca.producthuntclient.data.api.json.JsonCategory
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * REST service to talk to ProductHunt.
 */
interface ApiService {

  @GET("categories")
  fun getCategories(): Flowable<JsonCategories>
}