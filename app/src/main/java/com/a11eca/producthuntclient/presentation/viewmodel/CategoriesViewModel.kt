package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.entity.Post
import com.a11eca.producthuntclient.domain.usecase.GetCategoryUseCase
import com.a11eca.producthuntclient.domain.usecase.GetPostsUseCase
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import com.a11eca.producthuntclient.presentation.livedata.LiveItems
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

  fun getCategories(): LiveItems<CategoriesData> {
    return addLocalScopeDisposable(getPostsUseCase.getFilter()
        .switchMap {
          filterCategory ->
          getCategoryUseCase.getCategories()
              .map { categories ->
                CategoriesData(categories.sortedBy {
                  (id, slug) ->
                  if (slug == filterCategory) {
                    -1L
                  } else {
                    id
                  }
                })
              }
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(LiveFlow<CategoriesData>()))
  }

  fun setPostsFilter(categorySlug: String) {
    addLocalScopeDisposable(getPostsUseCase.setFilter(categorySlug)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({}, {}))
  }

  fun getPosts(): LiveFlow<List<Post>> {
    return addLocalScopeDisposable(getPostsUseCase.getFilter()
        .switchMap {
          category ->
          getPostsUseCase.getFiltered(category)
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(LiveFlow<List<Post>>())
    )
  }
}