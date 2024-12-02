package com.warrantysafe.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.SearchBar
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.productCard.ProductCard


// Data class for ProductCard
data class Product(
    val title: String,
    val purchase: String,
    val description: String,
    val progress: Float,
    val imageResId: Int
)

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(products) { product ->
            ProductCard(
                title = product.title,
                purchase = product.purchase,
                description = product.description,
                progress = product.progress,
                imageResId = product.imageResId
            )
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavController
) {

    val products = listOf(
        Product(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Whirlpool 5-star inverter AC",
            purchase = "30/11/2024",
            description = "5 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "TVS Raider 125cc",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Whirlpool 5-star inverter AC",
            purchase = "30/11/2024",
            description = "5 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "TVS Raider 125cc",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Whirlpool 5-star inverter AC",
            purchase = "30/11/2024",
            description = "5 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "TVS Raider 125cc",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Whirlpool 5-star inverter AC",
            purchase = "30/11/2024",
            description = "5 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "TVS Raider 125cc",
            purchase = "30/11/2024",
            description = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clip(shape = RoundedCornerShape(28.dp))
                .clickable {
                    navController.navigate(route = Route.SearchScreen.route)
                }
        ) {
            SearchBar(
                query = "",
                enabled = false,
                onQueryChange = {},
                onSearch = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Distribute components to start and end
            verticalAlignment = Alignment.CenterVertically // Center items vertically
        ) {
            // First Box (Sort By Section)
            Box(
                modifier = Modifier
                    .background(color = colorResource(R.color.white))
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = colorResource(R.color.black)
                    )
                    .padding(start = 8.dp) // Padding for spacing within the Box
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically // Align items vertically
                ) {
                    Text(
                        text = "Sort By",
                        modifier = Modifier.padding(end = 4.dp) // Space between text and icon
                    )
                    Icon(
                        modifier = Modifier
                            .size(24.dp), // Define a consistent size for the icon
                        painter = painterResource(R.drawable.drop_down),
                        contentDescription = null
                    )
                }
            }

            // Second Box (Filter Section)
            Box(
                modifier = Modifier
                    .background(color = colorResource(R.color.white))
                    .border(
                        width = 1.dp, shape = RectangleShape,
                        color = colorResource(R.color.black)
                    )
                    .padding(end = 8.dp) // Padding for spacing within the Box
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically // Align items vertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp) // Define a consistent size for the icon
                            .padding(start = 8.dp), // Space between icon and text
                        painter = painterResource(R.drawable.filter),
                        contentDescription = null
                    )
                    Text(
                        text = "Filter"
                    )
                }
            }
        }

        ProductList(products = products)
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val homeNavController = rememberNavController()
    HomeScreen(
        homeNavController
    )
}