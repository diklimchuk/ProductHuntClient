package com.a11eca.producthuntclient.data.mapping

import com.a11eca.producthuntclient.data.api.json.JsonCollection
import com.a11eca.producthuntclient.domain.entity.ProductCollection


fun jsonToCollection(json: JsonCollection): ProductCollection {
  val title = json.title ?: ProductCollection.NO_TITLE
  val thumbnail = json.background_image_url ?: ProductCollection.NO_THUMBNAIL
  return ProductCollection(json.id, json.name, title, json.subscriber_count, thumbnail)
}

fun jsonsToCollections(list: List<JsonCollection>): List<ProductCollection> {
  return list.map { jsonToCollection(it) }
}