package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.usecase.GetCategoryUseCase
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import com.a11eca.producthuntclient.presentation.livedata.LiveItems
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
): BaseViewModel() {

  fun getCategories(): LiveItems<CategoriesData> {
    return addLocalScopeDisposable(useCase.getCategories()
        .map { categories -> CategoriesData(categories) }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(LiveFlow<CategoriesData>()))
  }
}