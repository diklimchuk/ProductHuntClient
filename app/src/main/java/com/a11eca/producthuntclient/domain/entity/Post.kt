package com.a11eca.producthuntclient.domain.entity

data class Post(
    val id: Long,
    val name: String,
    val description: String,
    val votesCount: Long,
    val thumbnailUrl: String
)