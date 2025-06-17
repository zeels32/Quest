package com.quests.demo.products.presentation.ui.search.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quests.demo.events.presentation.events.components.TitleBar
import com.quests.demo.products.presentation.ui.search.ProductSearchViewModel
import com.quests.demo.products.presentation.ui.search.model.product.ProductUiState

@Composable
fun ProductScreen(
    productSearchViewModel: ProductSearchViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = { }
) {

    Scaffold(
        topBar = {
            TitleBar(
                text = "Products",
                isBackEnabled = true,
                onBack = onBackPressed,
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                ProductContent(
                    productSearchViewModel = productSearchViewModel
                )
            }
        }
    )


}

@Composable
fun ProductContent(
    productSearchViewModel: ProductSearchViewModel
) {

    val query = remember { mutableStateOf("") }

    ProductSearchBar(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        query = query.value,
        onQueryChange = {
            query.value = it
        },
    )

    val productUiState by productSearchViewModel.productUiState.collectAsState()

    when (productUiState) {
        is ProductUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ProductUiState.Success -> {
            ProductGrid(
                productList = (productUiState as ProductUiState.Success).products
            )
        }

        is ProductUiState.Error -> {
            ShowError((productUiState as ProductUiState.Error).message)
        }
    }
}