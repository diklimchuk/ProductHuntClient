package com.a11eca.producthuntclient.data.api.service

import com.a11eca.producthuntclient.data.api.json.JsonCategories
import com.a11eca.producthuntclient.data.api.json.JsonCollections
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST service to talk to ProductHunt.
 */
interface ApiService {

  @GET("categories")
  fun getCategories(): Flowable<JsonCategories>

  @GET("collections")
  fun getFilteredCollections(@Query("\"search[category]\"") categoryId: Long): Flowable<JsonCollections>
}