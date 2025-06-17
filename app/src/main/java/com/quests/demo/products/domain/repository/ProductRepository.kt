package com.quests.demo.products.domain.repository

import com.quests.demo.products.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProductsFromApi(): Result<List<Product>>
    suspend fun addProductsToLocalDatabase(products: List<Product>)
    fun fetchProductsFromDb(): Flow<List<Product>?>
}