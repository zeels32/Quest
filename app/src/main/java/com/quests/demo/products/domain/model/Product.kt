package com.quests.demo.products.domain.model

data class Product(
    val id: Int,
    val title: String? = null,
    val price: Double? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val imageThumbnail: String? = null,
    val rating: Double? = null,
    val reviewCount: Int? = null
)
