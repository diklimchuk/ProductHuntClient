package com.a11eca.producthuntclient.data.datasource.collections

import javax.inject.Inject

/**
 * Chooses appropriate [CollectionsDataSource] for retrieving collections.
 */
class CollectionsDataSourceFactory @Inject constructor(
    private val apiDataSource: ApiCollectionsDataSource
) {
  fun choose(): CollectionsDataSource {
    return apiDataSource
  }
}