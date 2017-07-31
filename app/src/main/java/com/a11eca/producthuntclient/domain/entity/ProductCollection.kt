package com.a11eca.producthuntclient.domain.entity

data class ProductCollection(
    val id: Long,
    val name: String,
    val title: String,
    val upvoteNumber: Long,
    val thumbnailUrl: String
)