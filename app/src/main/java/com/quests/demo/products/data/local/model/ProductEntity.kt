package com.quests.demo.products.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String? = null,
    val price: Double? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val imageThumbnail: String? = null,
    val rating: Double? = null,
    val reviewCount: Int? = null
)