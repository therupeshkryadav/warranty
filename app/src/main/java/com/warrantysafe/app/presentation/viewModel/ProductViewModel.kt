package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.GetActiveProductsUseCase
import com.warrantysafe.app.domain.useCases.GetExpiredProductsUseCase
import com.warrantysafe.app.domain.useCases.GetProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getActiveProductsUseCase: GetActiveProductsUseCase,
    private val getExpiredProductsUseCase: GetExpiredProductsUseCase,
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    // State holders for all, active, and expired products
    var products = mutableStateOf<List<Product>>(mutableListOf())
    var activeProducts = mutableStateOf<List<Product>>(mutableListOf())
    var expiredProducts = mutableStateOf<List<Product>>(mutableListOf())

    // Current date to filter active and expired products
    private val currentDate = "04/01/2025"

    // Load all products
    fun loadProducts() {
        viewModelScope.launch {
            val fetchedProducts = getProductsUseCase()
            products.value = fetchedProducts
        }
    }

    // Load active products
    fun loadActiveProducts() {
        viewModelScope.launch {
            val fetchedActiveProducts = getActiveProductsUseCase(currentDate)
            activeProducts.value = fetchedActiveProducts
        }
    }

    // Load expired products
    fun loadExpiredProducts() {
        viewModelScope.launch {
            val fetchedExpiredProducts = getExpiredProductsUseCase(currentDate)
            expiredProducts.value = fetchedExpiredProducts
        }
    }

    // Add a new product
    fun addProduct(title: String, purchase: String, expiry: String, category: String, imageResId: Int) {
        viewModelScope.launch {
            addProductUseCase(title, purchase, expiry, category, imageResId)
            // Refresh all product lists after adding a new product
            loadProducts()
            loadActiveProducts()
            loadExpiredProducts()
        }
    }
}