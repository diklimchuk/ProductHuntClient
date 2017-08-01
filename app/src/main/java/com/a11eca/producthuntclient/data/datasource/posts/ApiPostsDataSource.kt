package com.a11eca.producthuntclient.data.datasource.posts

import com.a11eca.producthuntclient.data.api.json.JsonDetailedPost
import com.a11eca.producthuntclient.data.api.json.JsonPost
import com.a11eca.producthuntclient.data.api.service.ApiService
import io.reactivex.Flowable
import javax.inject.Inject

class ApiPostsDataSource @Inject constructor(
    private val service: ApiService
) : PostsDataSource {
  override fun getFilteredPosts(category: String, pageNumber: Long): Flowable<List<JsonPost>> {
    return service.getFilteredPosts(category, pageNumber)
        .map { it.posts }
  }

  override fun getDetailedPost(postId: Long): Flowable<JsonDetailedPost> {
    return service.getDetailedPost(postId)
        .map { it.post }
  }
}