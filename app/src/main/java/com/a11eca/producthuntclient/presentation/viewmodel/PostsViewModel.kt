package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.entity.Post
import com.a11eca.producthuntclient.domain.usecase.GetPostsUseCase
import com.a11eca.producthuntclient.presentation.adapter.ItemsProvider
import com.a11eca.producthuntclient.presentation.livedata.LiveCollector
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel(), ItemsProvider {

  private val postPages = BehaviorSubject.createDefault<Long>(1).toSerialized()

  val posts = addLocalScopeDisposable(getPostsUseCase.getFilter()
      .switchMap {
        filter ->
        postPages.toFlowable(BackpressureStrategy.BUFFER)
            .flatMap {
              pageNumber ->
              getPostsUseCase.getFiltered(filter, pageNumber)
            }
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(LiveCollector<Post>()))!!

  fun setPostsFilter(categorySlug: String) {
    val completable = Completable.concatArray(
        getPostsUseCase.setFilter(categorySlug),
        Completable.fromRunnable { postPages.onNext(1) }
    )
    addLocalScopeDisposable(completable
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({}, {}))
  }

  override fun onLoadMore(page: Long) {
    addLocalScopeDisposable(Completable
        .fromRunnable {
          if (postPages.blockingLatest().first() != page) {
            postPages.onNext(page)
          }
        }
        .subscribeOn(Schedulers.computation())
        .subscribe({}, {}))
  }
}