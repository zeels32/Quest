package com.quests.demo.products.data.repository

import com.quests.demo.presentation.util.exceptions.NoInternetException
import com.quests.demo.products.data.local.dao.ProductDao
import com.quests.demo.products.data.mapper.toProductDomain
import com.quests.demo.products.data.mapper.toProductEntityList
import com.quests.demo.products.data.remote.api.ProductService
import com.quests.demo.products.domain.model.Product
import com.quests.demo.products.domain.repository.ProductRepository
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