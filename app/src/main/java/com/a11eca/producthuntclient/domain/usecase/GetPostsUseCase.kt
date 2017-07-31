package com.a11eca.producthuntclient.domain.usecase

import com.a11eca.producthuntclient.domain.entity.Post
import com.a11eca.producthuntclient.domain.repo.PostsRepo
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repo: PostsRepo
) {

  companion object {
    private const val DEFAULT_FILTER = "tech"
  }

  fun getFilter(): Flowable<String> {
    return repo.getFilter(DEFAULT_FILTER)
  }

  fun setFilter(categorySlug: String): Completable {
    return repo.setFilter(categorySlug)
  }

  fun getFiltered(categorySlug: String): Flowable<List<Post>> {
    return repo.getPosts(categorySlug)
  }
}