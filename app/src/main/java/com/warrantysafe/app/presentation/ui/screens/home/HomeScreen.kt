package com.warrantysafe.app.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.home.components.tabs.ActiveTab
import com.warrantysafe.app.presentation.ui.screens.home.components.tabs.ExpiredTab
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    activeProducts: List<Product>,
    expiredProducts: List<Product>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {

        //Search Bar
        Box(Modifier.fillMaxWidth().padding(horizontal = 8.dp)){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(56.dp))
                    .background(color = colorResource(R.color.black))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(56.dp))
                            .background(color = colorResource(R.color.black))
                            .clickable {
                                navController.navigate(route = Route.SearchScreen.route)
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.search_warranty),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = "Search",
                                fontSize = 20.sp,
                                color = colorResource(R.color.xtreme)
                            )
                        }
                    }
                }

            }
        }

        val tabTitles = listOf("Active", "Expired")

        val pagerState = rememberPagerState(initialPage = 0,
            pageCount = { tabTitles.size })
        val scope = rememberCoroutineScope()

        // Custom TabRow with no click effect
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            tabTitles.forEachIndexed { index, title ->
                // Custom Tab Implementation
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp)) // Optional for styling
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Disables ripple effect
                        ) {
                            scope.launch { pagerState.animateScrollToPage(index) }
                        }
                        .padding(8.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // HorizontalPager for the content
        HorizontalPager(
            state = pagerState, // Synchronizes with TabRow
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // Content for each page
            when (page) {
                0 -> ActiveTab(
                    navController = navController,
                    activeProducts = activeProducts
                ) // Content for Tab 1
                1 -> ExpiredTab(
                    navController = navController,
                    expiredProducts = expiredProducts
                ) // Content for Tab 2
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        activeProducts = listOf(
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            )

        ),
        expiredProducts = listOf(
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            )
        )
    )
}