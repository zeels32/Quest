package com.product.demo.data.mapper

import com.product.demo.data.local.model.ProductEntity
import com.product.demo.data.remote.model.ProductResponse
import com.product.demo.domain.model.Product
import com.product.demo.presentation.ui.product_search.model.product.ProductUiModel


fun ProductResponse.toProductDomain(): List<Product>? {
    return this.products?.map { productResponse ->
        Product(
            id = productResponse?.id ?: throw IllegalArgumentException("Product ID is null"),
            title = productResponse.title,
            price = productResponse.price,
            description = productResponse.description,
            imageUrl = productResponse.images?.firstOrNull().orEmpty(),
            imageThumbnail = productResponse.thumbnail,
            rating = productResponse.rating,
            reviewCount = productResponse.reviews?.size ?: 0,
        )
    }
}

fun List<ProductEntity>.toProductDomain(): List<Product> {
    return this.map { productEntity ->
        Product(
            id = productEntity.id,
            title = productEntity.title,
            price = productEntity.price,
            description = productEntity.description,
            imageUrl = productEntity.imageUrl,
            imageThumbnail = productEntity.imageThumbnail,
            rating = productEntity.rating,
            reviewCount = productEntity.reviewCount,
        )
    }
}

fun List<Product>.toProductEntityList(): List<ProductEntity> {

    return this.map { product ->
        ProductEntity(
            id = product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            imageUrl = product.imageUrl,
            imageThumbnail = product.imageThumbnail,
            rating = product.rating,
            reviewCount = product.reviewCount,
        )
    }
}

fun List<Product>?.toProductUiModelList(): List<ProductUiModel>? {
    return this?.map { product ->
        ProductUiModel(
            id = product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            imageUrl = product.imageUrl,
            imageThumbnail = product.imageThumbnail,
            rating = product.rating,
            reviewCount = product.reviewCount,
        )
    }
}