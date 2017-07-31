package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.entity.ProductCollection
import com.a11eca.producthuntclient.domain.usecase.GetCategoryUseCase
import com.a11eca.producthuntclient.domain.usecase.GetCollectionsUseCase
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import com.a11eca.producthuntclient.presentation.livedata.LiveItems
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getCollectionsUseCase: GetCollectionsUseCase
) : BaseViewModel() {

  fun getCategories(): LiveItems<CategoriesData> {
    return addLocalScopeDisposable(getCategoryUseCase.getDefaultCategoryId()
        .toFlowable()
        .flatMap {
          defaultCategoryId ->
          getCategoryUseCase.getCategories()
              .map { categories ->
                CategoriesData(categories.sortedBy {
                  (id) ->
                  if (id == defaultCategoryId) {
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

  fun getCollections(categoryId: Long): LiveFlow<List<ProductCollection>> {
    return addLocalScopeDisposable(getCollectionsUseCase.getCollections(categoryId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(LiveFlow<List<ProductCollection>>())
    )
  }
}