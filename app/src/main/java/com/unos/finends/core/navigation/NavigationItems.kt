package com.unos.finends.core.navigation

import androidx.annotation.DrawableRes
import com.unos.finends.R

sealed class NavigationItems(
    val name: String,
    val route: String,
    @DrawableRes val icon: Int
) {
    object Home : NavigationItems("Home", "home", R.drawable.iconhome)
    object History : NavigationItems("History", "history", R.drawable.history)
    object Statistic : NavigationItems("Statistic", "statistic", R.drawable.userr)
    object Profile : NavigationItems("Profile", "profile", R.drawable.userr)
}
