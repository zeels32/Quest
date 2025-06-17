package com.quests.demo.products.presentation.ui.search.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.quests.demo.products.presentation.ui.search.model.product.ProductUiModel
import com.quests.demo.presentation.util.CoilImageLoader

@Composable
fun ProductGrid(productList: List<ProductUiModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(productList) { product ->
            ProductCard(productUi = product)
        }
    }
}

@Composable
fun ProductCard(productUi: ProductUiModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(productUi.imageUrl)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = productUi.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                imageLoader = CoilImageLoader.getImageLoader(LocalContext.current)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = productUi.title.orEmpty(),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp
            )

            Text(
                text = productUi.price.toString(),
                color = Color(0xFF388E3C),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )

            Text(
                text = productUi.description.orEmpty(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { index ->
                    val iconColor = if (index < (productUi.rating?.toInt() ?: 0)) Color(0xFFFFD700) else Color.LightGray
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "(${productUi.reviewCount})", fontSize = 12.sp)
            }
        }
    }
}


@Preview
@Composable
private fun PreviewProductCard() {
    ProductCard(
        productUi = ProductUiModel(
            id = 1,
            title = "Sample Product",
            description = "This is a sample product description.",
            imageUrl = "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/1.webp",
            price = "$19.99".toDouble(),
            rating = 4.5,
            reviewCount = 100
        )
    )
}
