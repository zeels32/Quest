package com.product.demo.presentation.ui.product_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.product.demo.data.mapper.toProductUiModelList
import com.product.demo.domain.usecase.FetchProductsUseCase
import com.product.demo.presentation.ui.product_search.model.product.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase,
) : ViewModel() {

    val productUiState: StateFlow<ProductUiState> = fetchProductsUseCase.invoke()
        .map { products ->
            if (products.isNullOrEmpty()) {
                ProductUiState.Error("No products available")
            } else {
                ProductUiState.Success(products.toProductUiModelList() ?: emptyList())
            }
        }
        .onStart {
            emit(ProductUiState.Loading)
        }
        .catch { e ->
            val message = if (e is HttpException) {
                "No Internet Connection"
            } else {
                e.message ?: "An unexpected error occurred"
            }
            emit(ProductUiState.Error(message))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProductUiState.Loading
        )

}
