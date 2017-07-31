package com.a11eca.producthuntclient.domain.entity

data class Category(
    val id: Long,
    val slug: String,
    val name: String,
    val color: String,
    val itemName: String
)
