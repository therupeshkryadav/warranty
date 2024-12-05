package com.warrantysafe.app.presentation.common.productList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.home.Product
import com.warrantysafe.app.presentation.common.productList.components.productCard.ProductCard


@Composable
fun ProductList(
    navController: NavController,
    itemTint: Color,
    productType: List<Product> // Changed to a flat list of products
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 48.dp)
    ) {
        items(productType) { product ->
            when (product) {
                is Product.Active -> {
                    ProductCard(
                        navController = navController,
                        title = product.title,
                        purchase = product.purchase,
                        period = product.period,
                        progress = product.progress,
                        imageResId = product.imageResId,
                        progressTint = colorResource(R.color.DaysLeft),
                        itemTint = itemTint,
                        detailsColor = MaterialTheme.colorScheme.onSurface
                    )
                }

                is Product.Expired -> {
                    ProductCard(
                        navController = navController,
                        title = product.title,
                        purchase = product.purchase,
                        period = product.period,
                        progress = product.progress,
                        progressTint = colorResource(R.color.noDaysLeft),
                        imageResId = product.imageResId,
                        itemTint = itemTint,
                        detailsColor = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductList() {
    val activeProducts = listOf(
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        )
    )

    ProductList(
        navController = rememberNavController(),
        itemTint = Color.Green,
        productType = activeProducts
    )
}
