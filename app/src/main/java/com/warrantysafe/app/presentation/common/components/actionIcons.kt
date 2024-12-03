package com.warrantysafe.app.presentation.common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navgraph.Route

@SuppressLint("ComposableNaming")
@Composable
fun actionIcons(
    navController: NavHostController,
    currentRoute: String
) {
    Row {
        when (currentRoute) {
            Route.HomeScreen.route, Route.ProfileScreen.route -> {
                // Home or Profile screen actions
                IconButton(onClick = { /* Handle Notifications Click */ }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notifications"
                    )
                }
                IconButton(onClick = { /* Handle More Options Click */ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More Options"
                    )
                }
            }
            Route.AddScreen.route -> {
                // Add Warranty screen action
                IconButton(onClick = {
                    // Clear back stack of Route.AddScreen.route
                    navController.popBackStack(Route.AddScreen.route, inclusive = true)

                    // Navigate to HomeScreen
                    navController.navigate(Route.HomeScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check Icon"
                    )
                }
            }
            Route.SearchScreen.route -> {
                // Search screen action
                IconButton(onClick = { /* Handle Search Click */ }) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = R.drawable.search_warranty),
                        contentDescription = "Search",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            else -> {
                // Fallback case if no matching route
            }
        }
    }
}

@Preview
@Composable
fun ActionsPreview() {
    actionIcons(
        navController = rememberNavController(),
        currentRoute = Route.HomeScreen.route
    )
}