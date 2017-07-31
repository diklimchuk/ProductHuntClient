package com.a11eca.producthuntclient.domain.usecase

import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.repo.CategoryRepo
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repo: CategoryRepo
) {

  companion object {
    const val DEFAULT_CATEGORY_NAME = "slug"
    const val DEFAULT_CATEGORY_ID_IF_NAME_NOT_FOUND = 0L
  }

  fun getCategories(): Flowable<List<Category>> {
    return repo.getCategories()
  }

  fun getDefaultCategoryId(): Single<Long> {
    return repo.getCategories()
        .map {
          categories ->
          categories.find { category -> category.slug == DEFAULT_CATEGORY_NAME }?.id ?: DEFAULT_CATEGORY_ID_IF_NAME_NOT_FOUND
        }
        .firstOrError()
  }
}