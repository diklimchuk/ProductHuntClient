package com.a11eca.producthuntclient.domain.repo

import com.a11eca.producthuntclient.domain.entity.ProductCollection
import io.reactivex.Flowable

interface CollectionsRepo {
  companion object {
    const val NO_ID = -1L
  }

  /**
   * Get page of [ProductCollection].
   *
   * @param categoryId filter to apply to the list of collections.
   */
  fun getCollections(categoryId: Long): Flowable<List<ProductCollection>>
}