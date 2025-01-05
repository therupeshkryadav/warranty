package com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar.components.actionIcons
import com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar.components.navigationIcons
import com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar.components.titleAppBar
import com.warrantysafe.app.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: androidx.navigation.NavHostController,
    currentRoute: String
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = {
                titleAppBar(currentRoute = currentRoute)
            },
            navigationIcon = {
                navigationIcons(
                    navController = navController,
                    currentRoute = currentRoute
                )
            },
            actions = {
                actionIcons(
                    navController = navController,
                    currentRoute = currentRoute
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = colorResource(R.color.black)
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = colorResource(R.color.xtreme))
        )
    }
}

@Preview(showBackground = true, name = "SmartCustomTopAppBar Previews")
@Composable
fun SmartCustomTopAppBarPreview() {
    val navController = rememberNavController()

    Column {
        // Home Screen Preview
        TopAppBar(
            navController = navController,
            currentRoute = Route.HomeScreen.route
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Screen Preview
        TopAppBar(
            navController = navController,
            currentRoute = Route.ProfileScreen.route
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Warranty Screen Preview
        TopAppBar(
            navController = navController,
            currentRoute = Route.AddScreen.route
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Screen Preview
        TopAppBar(
            navController = navController,
            currentRoute = Route.SearchScreen.route
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Unknown Route Preview
        TopAppBar(
            navController = navController,
            currentRoute = Route.ProductDetailsScreen.route
        )
    }
}
