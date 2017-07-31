package com.a11eca.producthuntclient.domain.repo

import com.a11eca.producthuntclient.domain.entity.Category
import io.reactivex.Flowable
import io.reactivex.Observable

interface CategoryRepo {
  fun getCategories(): Flowable<List<Category>>
}