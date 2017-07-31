package com.a11eca.producthuntclient.data.datasource.posts

import javax.inject.Inject

/**
 * Chooses appropriate [PostsDataSource] for retrieving posts.
 */
class PostsDataSourceFactory @Inject constructor(
    private val apiDataSource: ApiPostsDataSource
) {
  fun choose(): PostsDataSource {
    return apiDataSource
  }
}