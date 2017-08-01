package com.a11eca.producthuntclient.data.datasource.posts

import android.util.LruCache
import com.a11eca.producthuntclient.data.api.json.JsonDetailedPost
import com.a11eca.producthuntclient.data.api.json.JsonPost
import javax.inject.Inject

class PostsCacheImpl @Inject constructor(): PostsCache {

  companion object {
    private const val POST_PAGES_CACHE_SIZE = 30
    private const val POSTS_CACHE_SIZE = 10
  }

  private val postPages = LruCache<Pair<String, Long>, List<JsonPost>>(POST_PAGES_CACHE_SIZE)

  private val posts = LruCache<Long, JsonDetailedPost>(POSTS_CACHE_SIZE)

  private fun makePostPagesKey(category: String, pageNumber: Long) = Pair(category, pageNumber)

  override fun putPostPage(category: String, pageNumber: Long, posts: List<JsonPost>) {
    postPages.put(makePostPagesKey(category, pageNumber), posts)
  }

  override fun hasPostPage(category: String, pageNumber: Long): Boolean {
    return postPages.get(makePostPagesKey(category, pageNumber)) != null
  }

  override fun getPostPage(category: String, pageNumber: Long): List<JsonPost> {
    return postPages.get(makePostPagesKey(category, pageNumber))
  }

  override fun getPost(postId: Long): JsonDetailedPost {
    return posts.get(postId)
  }

  override fun putPost(postId: Long, post: JsonDetailedPost) {
    posts.put(postId, post)
  }

  override fun hasPost(postId: Long): Boolean {
    return posts.get(postId) != null
  }
}