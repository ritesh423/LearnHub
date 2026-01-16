package com.riteshapps.learnhub.presentation.navigation

import android.net.http.SslCertificate.saveState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        Routes.Home.route,
        Routes.Settings.route
    )

    // Bottom bar only on main screens
    val showBottomBar = currentRoute in listOf(
        Routes.Home.route,
        Routes.Settings.route
    )

    val items = listOf(
        BottomNavigationItem(
            screen = Routes.Home,
            selectedIcon = Icons.Filled.Search,
            unSelectedIcon = Icons.Outlined.Search,
            hasNews = false
        ),
        BottomNavigationItem(
            screen = Routes.Settings,
            selectedIcon = Icons.Filled.Settings,
            unSelectedIcon = Icons.Outlined.Settings,
            hasNews = false
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            if (showBottomBar) {
                TopAppBar(
                    title = {
                        Text(
                            text = items.firstOrNull {
                                it.screen.route == currentRoute
                            }?.screen?.title ?: ""
                        )
                    }
                )
            }
        },

        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    items.forEach { item ->
                        val selected = currentRoute == item.screen.route

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = { Text(item.screen.title) },
                            icon = {
                                Icon(
                                    imageVector = if (selected)
                                        item.selectedIcon
                                    else
                                        item.unSelectedIcon,
                                    contentDescription = item.screen.title
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Routes.Login.route) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        "Login Screen",
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            composable(Routes.Register.route) {
                Text("Register Screen", modifier = Modifier.fillMaxSize())
            }

            composable(Routes.Home.route) {
                Text("Home Screen", modifier = Modifier.fillMaxSize())
            }

            composable(Routes.Settings.route) {
                Text("Settings Screen", modifier = Modifier.fillMaxSize())
            }

            composable(Routes.Detail.route) {
                Text("Detail Screen", modifier = Modifier.fillMaxSize())
            }
        }

    }

}
