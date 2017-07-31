package com.a11eca.producthuntclient.domain.usecase

import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.repo.CategoryRepo
import io.reactivex.Observable
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repo: CategoryRepo
) {
  fun getCategories(): Observable<List<Category>> {
    return repo.getCategories()
  }
}