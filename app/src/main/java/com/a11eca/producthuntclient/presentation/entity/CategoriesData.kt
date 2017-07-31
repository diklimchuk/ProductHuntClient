package com.a11eca.producthuntclient.presentation.entity

import com.a11eca.producthuntclient.domain.entity.Category

class CategoriesData(val categories: List<Category>) {
  companion object {
    const val KEY_NAME = "Name"
  }

  val data: List<Map<String, String>>
  val from = arrayOf(KEY_NAME)

  init {
    data = categories.map {
      category -> mapOf(Pair(KEY_NAME, category.name))
    }
  }
}