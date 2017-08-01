package com.a11eca.producthuntclient.data.datasource.posts

import com.a11eca.producthuntclient.data.api.json.JsonDetailedPost
import com.a11eca.producthuntclient.data.api.json.JsonPost

interface PostsCache {
  fun putPostPage(category: String, pageNumber: Long, posts: List<JsonPost>)

  fun hasPostPage(category: String, pageNumber: Long): Boolean

  fun getPostPage(category: String, pageNumber: Long): List<JsonPost>

  fun putPost(postId: Long, post: JsonDetailedPost)

  fun hasPost(postId: Long): Boolean

  fun getPost(postId: Long): JsonDetailedPost
}