package com.a11eca.producthuntclient.data.repo

import com.a11eca.producthuntclient.data.datasource.posts.PostsDataSourceFactory
import com.a11eca.producthuntclient.data.mapping.jsonToDetailedPost
import com.a11eca.producthuntclient.data.mapping.jsonsToPosts
import com.a11eca.producthuntclient.domain.entity.DetailedPost
import com.a11eca.producthuntclient.domain.entity.Post
import com.a11eca.producthuntclient.domain.repo.PostsRepo
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val dataSourceFactory: PostsDataSourceFactory
) : PostsRepo {

  private var filterInitialized = false
  private val postsFilter = BehaviorSubject.create<String>().toSerialized()

  override fun getPosts(category: String): Flowable<List<Post>> {
    return dataSourceFactory.choose()
        .getFilteredPosts(category)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map(::jsonsToPosts)
  }

  override fun getDetailedPost(postId: Long): Flowable<DetailedPost> {
    return dataSourceFactory.choose()
        .getDetailedPost(postId)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map(::jsonToDetailedPost)
  }

  override fun setFilter(category: String): Completable {
    return Completable.fromRunnable {
      if (postsFilter.blockingLatest().first() != category) {
        postsFilter.onNext(category)
      }
    }
  }

  override fun getFilter(defaultCategory: String): Flowable<String> {
    if (!filterInitialized) {
      postsFilter.onNext(defaultCategory)
    }
    return postsFilter.toFlowable(BackpressureStrategy.LATEST)
  }
}