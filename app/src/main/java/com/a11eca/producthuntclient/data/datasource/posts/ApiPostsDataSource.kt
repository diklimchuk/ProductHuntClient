package com.a11eca.producthuntclient.data.datasource.posts

import com.a11eca.producthuntclient.data.api.json.JsonPost
import com.a11eca.producthuntclient.data.api.service.ApiService
import io.reactivex.Flowable
import javax.inject.Inject

class ApiPostsDataSource @Inject constructor(
    private val service: ApiService
) : PostsDataSource {
  override fun getFilteredPosts(category: String): Flowable<List<JsonPost>> {
    return service.getFilteredPosts(category)
        .map { it.posts }
  }
}