package com.a11eca.producthuntclient.domain.repo

import com.a11eca.producthuntclient.domain.entity.DetailedPost
import com.a11eca.producthuntclient.domain.entity.Post
import io.reactivex.Completable
import io.reactivex.Flowable

interface PostsRepo {
  /**
   * Get list of [Post] for today.
   *
   * @param category Name of the category. Filter to apply to the list of posts.
   */
  fun getPosts(category: String, pageNumber: Long): Flowable<List<Post>>

  fun getDetailedPost(postId: Long): Flowable<DetailedPost>

  fun getFilter(defaultCategory: String): Flowable<String>

  fun setFilter(category: String): Completable
}