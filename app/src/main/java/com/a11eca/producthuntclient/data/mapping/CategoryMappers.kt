package com.a11eca.producthuntclient.data.mapping

import com.a11eca.producthuntclient.data.api.json.JsonCategory
import com.a11eca.producthuntclient.domain.entity.Category

fun jsonToCategory(json: JsonCategory): Category {
  return Category(json.id, json.slug, json.name, json.color, json.item_name)
}

fun jsonsToCategories(list: List<JsonCategory>): List<Category> {
  return list.map { jsonToCategory(it) }
}