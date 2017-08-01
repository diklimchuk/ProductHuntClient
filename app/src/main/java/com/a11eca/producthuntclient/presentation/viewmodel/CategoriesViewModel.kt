package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.usecase.GetCategoryUseCase
import com.a11eca.producthuntclient.domain.usecase.GetPostsUseCase
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

  val categories = addLocalScopeDisposable(getPostsUseCase.getDefaultFilter()
      .switchMap {
        filterCategory ->
        getCategoryUseCase.getCategories()
            .map { categories -> CategoriesData(sortCategories(filterCategory, categories)) }
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(LiveFlow<CategoriesData>()))!!


  private fun sortCategories(filterCategorySlug: String, categories: List<Category>): List<Category> {
    return categories.sortedBy {
      (id, slug) ->
      if (slug == filterCategorySlug) -1L else id
    }
  }
}