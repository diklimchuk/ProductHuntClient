package com.a11eca.producthuntclient.data.mapping

import com.a11eca.producthuntclient.data.api.json.JsonPost
import com.a11eca.producthuntclient.domain.entity.Post


fun jsonToPost(json: JsonPost): Post {
  return Post(json.id, json.name, json.tagline, json.votes_count, json.thumbnail.image_url)
}

fun jsonsToPosts(list: List<JsonPost>): List<Post> {
  return list.map { jsonToPost(it) }
}