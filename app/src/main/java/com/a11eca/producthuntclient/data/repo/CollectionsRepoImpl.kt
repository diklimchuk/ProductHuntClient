package com.a11eca.producthuntclient.data.repo

import com.a11eca.producthuntclient.data.datasource.collections.CollectionsDataSourceFactory
import com.a11eca.producthuntclient.data.mapping.jsonsToCollections
import com.a11eca.producthuntclient.domain.entity.ProductCollection
import com.a11eca.producthuntclient.domain.repo.CollectionsRepo
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CollectionsRepoImpl @Inject constructor(
    private val dataSourceFactory: CollectionsDataSourceFactory
) : CollectionsRepo {
  override fun getCollections(categoryId: Long): Flowable<List<ProductCollection>> {
    return dataSourceFactory.choose()
        .getFilteredCollections(categoryId)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map(::jsonsToCollections)
  }
}