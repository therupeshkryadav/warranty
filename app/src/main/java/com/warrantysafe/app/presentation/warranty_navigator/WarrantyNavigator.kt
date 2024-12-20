package com.warrantysafe.app.presentation.warranty_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.add.AddScreen
import com.warrantysafe.app.presentation.common.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.common.sideDrawer.SideDrawerContent
import com.warrantysafe.app.presentation.home.HomeScreen
import com.warrantysafe.app.presentation.home.components.productDetailsScreen.ProductDetailsScreen
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.notifification.NotificationScreen
import com.warrantysafe.app.presentation.profile.ProfileScreen
import com.warrantysafe.app.presentation.profile.edit.EditProfileScreen
import com.warrantysafe.app.presentation.search.SearchScreen
import com.warrantysafe.app.presentation.warranty_navigator.components.BottomNavigationItem
import com.warrantysafe.app.presentation.warranty_navigator.components.WarrantyBottomNavigation
import com.warrantysafe.app.utils.toRoute
import kotlinx.coroutines.launch

@Composable
fun WarrantyNavigator(
    navController: NavHostController,
    startDestination: String
) {

    val backStackState = navController.currentBackStackEntryAsState().value


    // Show bottom navigation for specific routes
    val isBottomBarVisible = backStackState?.destination?.route in listOf(
        Route.HomeScreen.route,
        Route.ProfileScreen.route
    )

    // Observe the current route for top bar logic
    val currentRoute = backStackState?.destination?.route

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.home_warranty,
                text = "Home",
                route = Route.HomeScreen
            ),
            BottomNavigationItem(
                icon = R.drawable.add_warranty,
                text = "Add",
                route = Route.AddScreen
            ),
            BottomNavigationItem(
                icon = R.drawable.profile_warranty,
                text = "Profile",
                route = Route.ProfileScreen
            )
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // State to manage the visibility of the dropdown menu
    val isMenuExpanded by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideDrawerContent(
                onItemClicked = { item ->
                    coroutineScope.launch { drawerState.close() }
                    // Perform navigation or actions based on the clicked item
                    when (item) {
                        "Home" -> {
                            // Handle Home navigation
                        }

                        "Profile" -> {
                            // Handle Profile navigation
                        }

                        "Settings" -> {
                            // Handle Settings navigation
                        }

                        "Logout" -> {
                            // Handle Logout
                        }
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                currentRoute?.let {
                    CustomTopAppBar(
                        navController = navController,
                        currentRoute = it,
                        drawerState = drawerState
                    )
                }
            },
            bottomBar = {
                if (isBottomBarVisible) {
                    WarrantyBottomNavigation(
                        items = bottomNavigationItems,
                        currentRoute = currentRoute.toRoute()!!,
                        onItemClick = { route ->
                            route?.let {
                                navigateToTab(navController, it)
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            // Navigation host with padding applied
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Route.HomeScreen.route) {
                    HomeScreen(navController = navController)
                }
                composable(Route.AddScreen.route) {
                    AddScreen(navController = navController)
                }
                composable(Route.NotificationScreen.route) {
                    NotificationScreen(navController = navController)
                }
                composable(
                    route = "productDetailsScreen/{productName}/{purchaseDate}/{expiryDate}/{progress}/{period}",
                    arguments = listOf(
                        navArgument("productName") { type = NavType.StringType },
                        navArgument("purchaseDate") { type = NavType.StringType },
                        navArgument("expiryDate") { type = NavType.StringType },
                        navArgument("progress") { type = NavType.FloatType },
                        navArgument("period") { type = NavType.StringType }
                    )
                ) {
                    val productName = it.arguments?.getString("productName") ?: "Unknown"
                    val purchaseDate = it.arguments?.getString("purchaseDate") ?: "N/A"
                    val expiryDate = it.arguments?.getString("expiryDate") ?: "N/A"
                    val progress = it.arguments?.getFloat("progress") ?: 0f
                    val period = it.arguments?.getString("period") ?: "N/A"

                    //Navigate to ProductDetailsScreen -->
                    ProductDetailsScreen(
                        navController = navController,
                        productName = productName,
                        purchaseDate = purchaseDate,
                        expiryDate = expiryDate,
                        progress = progress,
                        period = period
                    )
                }

                composable(
                    route = "editProfileScreen/{fullName}/{userName}/{emailId}/{phone}",
                    arguments = listOf(
                        navArgument("fullName") { type = NavType.StringType },
                        navArgument("userName") { type = NavType.StringType },
                        navArgument("emailId") { type = NavType.StringType },
                        navArgument("phone") { type = NavType.StringType }
                    )
                ) {
                    val fullName = it.arguments?.getString("fullName") ?: "----"
                    val userName = it.arguments?.getString("userName") ?: "----"
                    val emailId = it.arguments?.getString("emailId") ?: "----"
                    val phone = it.arguments?.getString("phone") ?: "----"

                    //Navigate to EditProfileScreen -->
                    EditProfileScreen(
                        navController = navController,
                        fullName = fullName,
                        userName = userName,
                        emailId = emailId,
                        phoneNumber = phone
                    )
                }

                composable(Route.ProfileScreen.route) {
                    ProfileScreen(navController = navController)
                }
                // Search Flow (Search Screen)
                composable(Route.SearchScreen.route) {
                    SearchScreen(navController = navController)
                }
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.route) {
        popUpTo(navController.graph.startDestinationId) {
            inclusive = (route == Route.ProfileScreen) // Apply inclusive only for ProfileScreen
            saveState = true // Save state for tabs
        }
        launchSingleTop = true // Avoid multiple instances of the same destination
        restoreState = true // Restore the state if previously saved
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWarranty() {
    WarrantyNavigator(
        navController = rememberNavController(),
        startDestination = Route.HomeScreen.route
    )
}
