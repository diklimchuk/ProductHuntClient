package com.a11eca.producthuntclient.data.datasource.posts

import javax.inject.Inject

/**
 * Chooses appropriate [PostsDataSource] for retrieving posts.
 */
class PostsDataSourceFactory @Inject constructor(
    private val apiDataSource: ApiPostsDataSource,
    private val postsCache: PostsCache
) {

  fun choosePostSource(postId: Long): PostsDataSource {
    if (postsCache.hasPost(postId)) {
      return MemoryPostsDataSource(postsCache)
    }
    return apiDataSource
  }

  fun choosePostPageSource(category: String, pageNumber: Long): PostsDataSource {
    if (postsCache.hasPostPage(category, pageNumber)) {
      return MemoryPostsDataSource(postsCache)
    }
    return apiDataSource
  }
}