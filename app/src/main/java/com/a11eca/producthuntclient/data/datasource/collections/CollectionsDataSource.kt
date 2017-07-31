package com.a11eca.producthuntclient.data.datasource.collections

import com.a11eca.producthuntclient.data.api.json.JsonCollection
import io.reactivex.Flowable

interface CollectionsDataSource {
  fun getFilteredCollections(categoryId: Long): Flowable<List<JsonCollection>>
}