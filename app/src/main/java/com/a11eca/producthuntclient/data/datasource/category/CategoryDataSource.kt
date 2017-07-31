package com.a11eca.producthuntclient.data.datasource.category

import com.a11eca.producthuntclient.data.api.json.JsonCategory
import io.reactivex.Flowable

interface CategoryDataSource {

  fun getCategories(): Flowable<List<JsonCategory>>
}