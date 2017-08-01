package com.a11eca.producthuntclient.data.datasource.posts

import com.a11eca.producthuntclient.data.api.json.JsonDetailedPost
import com.a11eca.producthuntclient.data.api.json.JsonPost
import io.reactivex.Flowable

class MemoryPostsDataSource constructor(
    private val cache: PostsCache
): PostsDataSource {
  override fun getFilteredPosts(category: String, pageNumber: Long): Flowable<List<JsonPost>> {
    return Flowable.just(cache.getPostPage(category, pageNumber))
  }

  override fun getDetailedPost(postId: Long): Flowable<JsonDetailedPost> {
    return Flowable.just(cache.getPost(postId))
  }
}