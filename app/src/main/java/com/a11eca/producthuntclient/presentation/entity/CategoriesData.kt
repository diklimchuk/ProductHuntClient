package com.a11eca.producthuntclient.presentation.entity

import com.a11eca.producthuntclient.domain.entity.Category

class CategoriesData(val categories: List<Category>) {
  companion object {
    const val KEY_ID = "Id"
    const val KEY_NAME = "Name"
  }

  val data: List<Map<String, Any>>
  val from = arrayOf(KEY_NAME)

  init {
    data = categories.sortedBy { (id) -> id }.map {
      (id, name) ->
      mapOf(Pair(KEY_ID, id), Pair(KEY_NAME, name))
    }
  }
}