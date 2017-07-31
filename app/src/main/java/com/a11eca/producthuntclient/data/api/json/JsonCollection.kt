package com.a11eca.producthuntclient.data.api.json

data class JsonCollection(
    val id: Long,
    val name: String,
    val title: String?,
    val subscriber_count: Long,
    val background_image_url: String?
)