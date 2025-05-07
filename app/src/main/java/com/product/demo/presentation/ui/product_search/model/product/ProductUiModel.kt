package com.product.demo.presentation.ui.product_search.model.product

data class ProductUiModel(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val imageThumbnail: String? = null,
    val rating: Double? = 0.0,
    val reviewCount: Int? = null
)

sealed interface ProductUiState {
    data object Loading : ProductUiState
    data class Success(val products: List<ProductUiModel>) : ProductUiState
    data class Error(val message: String) : ProductUiState
}