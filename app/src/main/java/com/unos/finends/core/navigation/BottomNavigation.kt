package com.unos.finends.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.unos.finends.auth
import com.unos.finends.features.history.HistoryScreen
import com.unos.finends.features.profile.ProfileScreen
import com.unos.finends.features.statistic.StatisticScreen
import com.unos.finends.features.home.HomeScreen

@Composable
fun BottomNavigation(navController: NavHostController) {
    val navigationItems = listOf(
        NavigationItems.Home,
        NavigationItems.History,
        NavigationItems.Statistic,
        NavigationItems.Profile
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route

                navigationItems.forEach { navItem ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(id = navItem.icon),
                                contentDescription = navItem.name
                            )
                        },
                        selected = currentDestination == navItem.route,
                        onClick = {
                            if (currentDestination != navItem.route) {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationItems.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationItems.Home.route) {
                HomeScreen(
                    navController = navController,
                    currentUser = auth.currentUser,
                    onSignOutClick = { }
                )
            }
            composable(NavigationItems.Statistic.route) {
                StatisticScreen( navController = navController)
            }
            composable(NavigationItems.History.route) {
                HistoryScreen(navController = navController)
            }
            composable(NavigationItems.Profile.route) {
               ProfileScreen(navController = navController, currentUser = auth.currentUser )
            }
        }
    }
}
