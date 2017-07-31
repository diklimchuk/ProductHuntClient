package com.a11eca.producthuntclient.data.api.service

import com.a11eca.producthuntclient.data.api.json.JsonCategories
import com.a11eca.producthuntclient.data.api.json.JsonPosts
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST service to talk to ProductHunt.
 */
interface ApiService {

  @GET("categories")
  fun getCategories(): Flowable<JsonCategories>

  @GET("posts/all")
  fun getFilteredPosts(@Query("\"search[category]\"") category: String): Flowable<JsonPosts>
}