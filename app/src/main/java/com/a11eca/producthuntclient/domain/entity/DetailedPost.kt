package com.a11eca.producthuntclient.domain.entity

data class DetailedPost(
    val id: Long,
    val name: String,
    val description: String,
    val votesCount: Long,
    val screenshotUrl: String,
    val productLink: String
)