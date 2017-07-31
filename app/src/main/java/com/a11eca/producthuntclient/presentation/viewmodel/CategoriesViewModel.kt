package com.a11eca.producthuntclient.presentation.viewmodel

import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.usecase.GetCategoryUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
): BaseViewModel() {

  fun getCategories(): Flowable<List<Category>> {
    return useCase.getCategories()
  }
}