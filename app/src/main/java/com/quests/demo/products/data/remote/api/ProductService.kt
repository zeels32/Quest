package com.quests.demo.products.data.remote.api

import com.quests.demo.products.data.remote.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun fetchProductsAsync(): Response<ProductResponse>
}