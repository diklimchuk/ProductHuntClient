package com.a11eca.producthuntclient.domain.repo

import com.a11eca.producthuntclient.domain.entity.Category
import io.reactivex.Observable

interface CategoryRepo {
  fun getCategories(): Observable<List<Category>>
}