package com.a11eca.producthuntclient.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.entity.Post
import com.a11eca.producthuntclient.domain.usecase.GetCategoryUseCase
import com.a11eca.producthuntclient.domain.usecase.GetPostsUseCase
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import com.a11eca.producthuntclient.presentation.livedata.LiveItems
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

  val categories = addLocalScopeDisposable(getPostsUseCase.getFilter()
      .switchMap {
        filterCategory ->
        getCategoryUseCase.getCategories()
            .map { categories -> CategoriesData(sortCategories(filterCategory, categories)) }
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(LiveFlow<CategoriesData>()))!!

  val posts = addLocalScopeDisposable(getPostsUseCase.getFilter()
      .switchMap {
        filter ->
        addEmptyPostsList(getPostsUseCase.getFiltered(filter))
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(LiveFlow<List<Post>>()))!!

  fun setPostsFilter(categorySlug: String) {
    addLocalScopeDisposable(getPostsUseCase.setFilter(categorySlug)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({}, {}))
  }

  private fun addEmptyPostsList(flowable: Flowable<List<Post>>): Flowable<List<Post>> {
    return Flowable.just(listOf<Post>()).concatWith(flowable)
  }

  private fun sortCategories(filterCategorySlug: String, categories: List<Category>): List<Category> {
    return categories.sortedBy {
      (id, slug) ->
      if (slug == filterCategorySlug) -1L else id
    }
  }
}