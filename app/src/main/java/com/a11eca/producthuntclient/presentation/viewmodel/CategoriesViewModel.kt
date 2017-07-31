package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.usecase.GetCategoryUseCase
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import com.a11eca.producthuntclient.presentation.livedata.LiveItems
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
): BaseViewModel() {

  fun getCategories(): LiveItems<List<Category>> {
    return addLocalScopeDisposable(useCase.getCategories()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(LiveFlow<List<Category>>()))
  }
}