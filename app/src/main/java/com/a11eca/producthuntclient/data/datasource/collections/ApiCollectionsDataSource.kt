package com.a11eca.producthuntclient.data.datasource.collections

import com.a11eca.producthuntclient.data.api.json.JsonCollection
import com.a11eca.producthuntclient.data.api.service.ApiService
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiCollectionsDataSource @Inject constructor(
    private val service: ApiService
) : CollectionsDataSource {
  override fun getFilteredCollections(categoryId: Long): Flowable<List<JsonCollection>> {
    return service.getFilteredCollections(categoryId)
        .map { it.collections }
  }
}