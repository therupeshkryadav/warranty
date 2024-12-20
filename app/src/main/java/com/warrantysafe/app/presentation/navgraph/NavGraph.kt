package com.warrantysafe.app.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.splash.SplashSheet
import com.warrantysafe.app.presentation.warranty_navigator.WarrantyNavigator

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    // App Start Flow (Splash to BottomNav)
    NavHost(navController, startDestination = Route.SplashSheet.route) {
        // SplashScreen to BottomNavigation
        composable(Route.SplashSheet.route) {
            SplashSheet(
                navController = navController,
                onFinish = {
                    navController.navigate(Route.BottomNavigation.route) {
                        popUpTo(Route.SplashSheet.route) { inclusive = true }
                    }
                })
        }
        // BottomNavigation Flow
        composable(Route.BottomNavigation.route) {
            val bottomNavController = rememberNavController()
            WarrantyNavigator(
                navController = bottomNavController,
                startDestination = Route.HomeScreen.route
            )
        }

    }
}