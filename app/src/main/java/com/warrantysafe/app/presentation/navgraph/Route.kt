package com.warrantysafe.app.presentation.navgraph

import android.net.Uri

sealed class Route(
    val route : String
){
    object SplashSheet : Route(route = "splashSheet")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "search_screen")
    object AddScreen : Route(route = "addScreen")
    object ProfileScreen : Route(route = "profileScreen")

    object ProductDetailsScreen : Route("productDetailsScreen/{productName}/{purchaseDate}/{expiryDate}/{progress}/{period}") {
        fun createRoute(
            productName: String?,
            purchaseDate: String?,
            expiryDate: String?,
            progress: Float?,
            period: String?
        ) :String{
            // Provide safe defaults and encode the values
            val safeProductName = Uri.encode(productName ?: "Unknown")
            val safePurchaseDate = Uri.encode(purchaseDate ?: "N/A")
            val safeExpiryDate = Uri.encode(expiryDate ?: "N/A")
            val safeProgress = progress?.toString() ?: "0.0"
            val safePeriod = Uri.encode(period ?: "N/A")
            return "productDetailsScreen/$safeProductName/$safePurchaseDate/$safeExpiryDate/$safeProgress/$safePeriod"}
    }

    object EditProfileScreen : Route("editProfileScreen/{fullName}/{userName}/{emailId}/{phone}") {
        fun createRoute(
            fullName: String?,
            userName: String?,
            emailId: String?,
            phone: String?
        ) :String{
            // Provide safe defaults and encode the values
            val safeFullName = Uri.encode(fullName ?: "----")
            val safeUserName = Uri.encode(userName ?: "----")
            val safeEmailId = Uri.encode(emailId ?: "----")
            val safePhone = Uri.encode(phone ?: "----")
            return "editProfileScreen/$safeFullName/$safeUserName/$safeEmailId/$safePhone"}
    }
    object NotificationScreen : Route(route = "notificationScreen")
    object BottomNavigation : Route(route = "bottomNavigation")
}