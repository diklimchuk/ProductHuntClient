package com.a11eca.producthuntclient.data.api.json

data class JsonDetailedPost(
    val id: Long,
    val name: String,
    val tagline: String,
    val votes_count: Long,
    val screenshot_url: JsonScreenshot,
    val discussion_url: String
) {
  companion object {
    data class JsonScreenshot(
        val `850px`: String
    )
  }
}