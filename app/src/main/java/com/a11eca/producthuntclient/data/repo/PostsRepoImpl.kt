package com.a11eca.producthuntclient.data.repo

import com.a11eca.producthuntclient.data.datasource.posts.PostsDataSourceFactory
import com.a11eca.producthuntclient.data.mapping.jsonsToPosts
import com.a11eca.producthuntclient.domain.entity.Post
import com.a11eca.producthuntclient.domain.repo.PostsRepo
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val dataSourceFactory: PostsDataSourceFactory
) : PostsRepo {
  override fun getPosts(category: String): Flowable<List<Post>> {
    return dataSourceFactory.choose()
        .getFilteredPosts(category)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map(::jsonsToPosts)
  }
}