package com.example.foodshare_mobile.ui.navigation

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val title: String,
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)
