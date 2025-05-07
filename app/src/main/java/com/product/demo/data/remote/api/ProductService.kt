package com.product.demo.data.remote.api

import com.product.demo.data.remote.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun fetchProductsAsync(): Response<ProductResponse>
}