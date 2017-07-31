package com.a11eca.producthuntclient.data.datasource.category

import javax.inject.Inject

/**
 * Chooses appropriate strategy for retrieving data.
 */
class CategoryDataSourceFactory @Inject constructor(
    private val apiDataSource: ApiCategoryDataSource
) {
  fun choose(): CategoryDataSource {
    return apiDataSource
  }
}