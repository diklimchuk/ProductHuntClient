package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.entity.DetailedPost
import com.a11eca.producthuntclient.domain.usecase.GetDetailedPostUseCase
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val useCase: GetDetailedPostUseCase
) : BaseViewModel() {

  private val postIdObservable = BehaviorSubject.create<Long>().toSerialized()

  val post = addLocalScopeDisposable(postIdObservable.toFlowable(BackpressureStrategy.LATEST)
      .switchMap {
        postId ->
        useCase.getDetailedPost(postId)
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(LiveFlow<DetailedPost>()))!!

  fun setPostId(postId: Long) {
    postIdObservable.onNext(postId)
  }
}