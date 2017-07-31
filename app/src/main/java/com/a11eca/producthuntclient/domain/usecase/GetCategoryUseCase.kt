package com.a11eca.producthuntclient.domain.usecase

import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.repo.CategoryRepo
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repo: CategoryRepo
) {

  fun getCategories(): Flowable<List<Category>> {
    return repo.getCategories()
  }
}