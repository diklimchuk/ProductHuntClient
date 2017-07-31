package com.a11eca.producthuntclient.data.repo

import com.a11eca.producthuntclient.data.datasource.category.CategoryDataSourceFactory
import com.a11eca.producthuntclient.data.mapping.jsonsToCategories
import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.repo.CategoryRepo
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryRepoImpl @Inject constructor(
    private val dataSourceFactory: CategoryDataSourceFactory
) : CategoryRepo {
  override fun getCategories(): Flowable<List<Category>> {
    return dataSourceFactory.choose()
        .getCategories()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map(::jsonsToCategories)
  }
}