package com.product.demo.data.repository

import com.product.demo.data.local.dao.ProductDao
import com.product.demo.data.mapper.toProductDomain
import com.product.demo.data.mapper.toProductEntityList
import com.product.demo.data.remote.api.ProductService
import com.product.demo.domain.exceptions.NoInternetException
import com.product.demo.domain.model.Product
import com.product.demo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDao: ProductDao,
) : ProductRepository {


    override suspend fun fetchProductsFromApi(): Result<List<Product>> {

        // Fetch products from the API

        return try {
            val response = productService.fetchProductsAsync()
            return if (response.isSuccessful) {
                val products = response.body()
                Result.success(products?.toProductDomain() ?: emptyList())
            } else {
                Result.failure(Exception("Error fetching products: ${response.message()}"))
            }
        } catch (e: HttpException) {
            Result.failure(NoInternetException(cause = e))
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override fun fetchProductsFromDb(): Flow<List<Product>?> {
        return productDao.getAllProducts()
            .map { productEntity ->
                productEntity?.toProductDomain()
            }
    }

    override suspend fun addProductsToLocalDatabase(products: List<Product>) {
        if (products.isNotEmpty()) {
            val productEntities = products.toProductEntityList()
            productDao.insertProducts(productEntities)
        }
    }

}