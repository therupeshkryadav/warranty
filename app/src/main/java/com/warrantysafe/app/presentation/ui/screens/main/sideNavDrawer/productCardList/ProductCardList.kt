package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.ProductCard
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.main.utils.dropDownMenu.components.dropDownMenuItem
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductCardList(
    navController: NavController
) {
    val productViewModel: ProductViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        productViewModel.loadAllProducts()
    }
    // Observe the active products state from the view model
    val allProductState = productViewModel.allProductsState.observeAsState()
    val allProducts = allProductState.value?.getOrNull() as? List<Product> ?: emptyList() // Safe casting and defaulting to empty list

    Log.d("ActiveProducts","in Tab:  $allProducts")
    val sortOptions = listOf(
        "Old to Recent",
        "Recent to Old"
    )
    val expandedSort = remember { mutableStateOf(false) }
    val selectedSortOption = remember { mutableStateOf("Sort By") }
    // State to keep track of selected products
    val selectedProducts = remember { mutableStateListOf<Product>() }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(
            title = {
                Text(
                    text = "List of Product Cards",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,  // Handling overflow text
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {}
        )
        if (allProducts.isNotEmpty()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // First Box (Sort By Section)
                Box(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Disables ripple effect
                        ) { expandedSort.value = true }
                        .border(
                            width = 1.dp,
                            shape = RectangleShape,
                            color = colorResource(R.color.black)
                        )
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Sort By",
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.drop_down),
                            contentDescription = null
                        )
                    }

                    DropdownMenu(
                        modifier = Modifier.wrapContentWidth(),
                        containerColor = Color.White,
                        expanded = expandedSort.value,
                        onDismissRequest = { expandedSort.value = false }
                    ) {
                        sortOptions.forEach { option ->
                            dropDownMenuItem(
                                item = option,
                                onClick = {
                                    selectedSortOption.value = option
                                    expandedSort.value = false
                                    applySorting(option, allProducts) // Sorting logic
                                }
                            )
                        }
                    }
                }
                if (selectedProducts.size > 0) {
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Disables ripple effect
                        ) { },
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 8.dp)
            ) {
                items(allProducts) { product ->
                    val isSelected = selectedProducts.contains(product)

                    Row {
                        if (isSelected) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            ProductCard(
                                productName = product.productName,
                                purchase = product.purchase,
                                expiry = product.expiry,
                                category = product.category,
                                imageResource = rememberAsyncImagePainter(product.productImageUri),
                                itemTint = Color.Transparent, // Indicate selection
                                detailsColor = Color.Black, // Change text color for selected item
                                onSlidingForward = {
                                        // Add to selected products
                                        selectedProducts.add(product)
                                },
                                onSlidingBackward = {
                                        // If already selected, deselect it
                                        selectedProducts.remove(product)
                                },
                                onClick = {
                                    navigateToDetails(product, navController)
                                }
                            )
                        }
                        else{
                            ProductCard(
                                productName = product.productName,
                                purchase = product.purchase,
                                expiry = product.expiry,
                                category = product.category,
                                imageResource = rememberAsyncImagePainter(product.productImageUri),
                                itemTint = Color.Transparent, // Indicate selection
                                detailsColor = Color.Black, // Change text color for selected item
                                onSlidingForward = {
                                        // Add to selected products
                                        selectedProducts.add(product)
                                },
                                onSlidingBackward = {
                                        // If already selected, deselect it
                                        selectedProducts.remove(product)
                                },
                                onClick = {
                                    navigateToDetails(product, navController)
                                }
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        } else {
            // Empty state UI
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "No Products",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "No Products, Add to display.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

    }
}

fun applySorting(option: String, products: List<Product>): List<Product> {
    return when (option) {
        "Old to Recent" -> products.sortedBy { it.purchase }
        "Recent to Old" -> products.sortedByDescending { it.purchase }
        else -> products // Default sorting (if needed)
    }
}


private fun navigateToDetails(product: Product, navController: NavController) {

    val route = Route.ProductDetailsScreen.createRoute(
        productId = "",
        productName = product.productName,  // Correct property name
        purchaseDate = product.purchase,
        category = product.category,
        expiryDate = product.expiry,
        notes = product.notes,
        imageUri = product.productImageUri.toUri()
    ) // Placeholder for expiry logic
    Log.d("fatal", "Navigating to route: $route")
    navController.navigate(route)
}