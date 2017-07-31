package com.a11eca.producthuntclient.data.repo

import com.a11eca.producthuntclient.domain.entity.Category
import com.a11eca.producthuntclient.domain.repo.CategoryRepo
import io.reactivex.Observable
import javax.inject.Inject

class CategoryRepoImpl @Inject constructor(

) : CategoryRepo {
  override fun getCategories(): Observable<List<Category>> {
    return Observable.empty()
  }
}