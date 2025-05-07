package com.product.demo.domain.usecase

import com.product.demo.domain.model.Product
import com.product.demo.domain.repository.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class FetchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {


    operator fun invoke(): Flow<List<Product>?> {

        return productRepository.fetchProductsFromDb()
            .flatMapLatest { products ->
                if (products?.isEmpty() == true) {
                    val result = productRepository.fetchProductsFromApi()
                    if (result.isSuccess) {
                        val productList = result.getOrNull() ?: emptyList()
                        productRepository.addProductsToLocalDatabase(productList)
                    }
                }
                productRepository.fetchProductsFromDb()
            }

    }

}