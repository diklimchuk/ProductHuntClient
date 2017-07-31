package com.a11eca.producthuntclient.data.api.json

data class JsonPost(
    val id: Long,
    val name: String,
    val tagline: String,
    val votes_count: Long,
    val thumbnail: JsonThumbnail
) {
  data class JsonThumbnail(
      val image_url: String
  )
}