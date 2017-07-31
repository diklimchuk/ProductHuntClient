package com.a11eca.producthuntclient.data.datasource.category

import com.a11eca.producthuntclient.data.api.json.JsonCategory
import com.a11eca.producthuntclient.data.api.service.ApiService
import io.reactivex.Flowable
import javax.inject.Inject

class ApiCategoryDataSource @Inject constructor(
    private val service: ApiService
): CategoryDataSource {
  override fun getCategories(): Flowable<List<JsonCategory>> {
    return service.getCategories()
  }
}