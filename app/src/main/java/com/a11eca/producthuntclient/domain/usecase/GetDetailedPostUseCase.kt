package com.a11eca.producthuntclient.domain.usecase

import com.a11eca.producthuntclient.domain.entity.DetailedPost
import com.a11eca.producthuntclient.domain.repo.PostsRepo
import io.reactivex.Flowable
import javax.inject.Inject

class GetDetailedPostUseCase @Inject constructor(
    private val repo: PostsRepo
) {
  fun getDetailedPost(postId: Long): Flowable<DetailedPost> {
    return repo.getDetailedPost(postId)
  }
}