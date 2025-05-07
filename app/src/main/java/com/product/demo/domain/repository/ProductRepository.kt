package com.product.demo.domain.repository

import com.product.demo.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProductsFromApi(): Result<List<Product>>
    suspend fun addProductsToLocalDatabase(products: List<Product>)
    fun fetchProductsFromDb(): Flow<List<Product>?>
}