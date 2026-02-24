package com.example.foodshare_mobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodshare_mobile.ui.navigation.BottomNavItem
import com.example.foodshare_mobile.ui.screens.DiscoverScreen
import com.example.foodshare_mobile.ui.screens.FoodDetailScreen
import com.example.foodshare_mobile.ui.screens.FoodSplashScreen
import com.example.foodshare_mobile.ui.screens.HomeScreen
import com.example.foodshare_mobile.ui.screens.RoleSelectionScreen
import com.example.foodshare_mobile.ui.theme.Foodshare_mobileTheme
import com.example.foodshare_mobile.ui.viewmodels.FoodViewModel

/**
 * The main entry point for the app's UI.
 */
@Composable
fun FoodShareApp() {
    var appStage by remember { mutableStateOf("onboarding") }

    when (appStage) {
        "onboarding" -> FoodSplashScreen(onFinished = { appStage = "role_selection" })
        "role_selection" -> RoleSelectionScreen(onFinished = { appStage = "dashboard" })
        "dashboard" -> MainContent()
    }
}

/**
 * This is the main application screen with Scaffold, TopAppBar, and Bottom Navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(foodViewModel: FoodViewModel = viewModel()) {
    // List of items for the bottom navigation bar
    val bottomNavItems = listOf(
        BottomNavItem("Home", "home", R.drawable.ic_home, R.drawable.ic_home),
        BottomNavItem("Discover", "discover", R.drawable.ic_discovery, R.drawable.ic_discovery),
        BottomNavItem("Shop", "shop", R.drawable.ic_shopping_bag, R.drawable.ic_shopping_bag),
        BottomNavItem("Profile", "profile", R.drawable.ic_profile, R.drawable.ic_profile)
    )
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val selectedFood by foodViewModel.selectedFood.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Search Bar
                    TextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Find a hotel/food") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search Icon"
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.LightGray.copy(alpha = 0.4f),
                            unfocusedContainerColor = Color.LightGray.copy(alpha = 0.4f),
                            disabledContainerColor = Color.LightGray.copy(alpha = 0.4f),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle profile click */ }) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle notification click */ }) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { 
                            selectedItemIndex = index 
                            // Optional: Clear selection when switching tabs
                            if (index != 1) foodViewModel.clearSelection()
                        },
                        label = { Text(item.title) },
                        icon = {
                            Icon(
                                painter = painterResource(id = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon),
                                contentDescription = item.title
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFD50000),
                            unselectedIconColor = Color.Gray,
                            indicatorColor = Color.Transparent // No background indicator
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        // The content of the currently selected screen goes here
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedItemIndex) {
                0 -> HomeScreen(userName = "Toxic")
                1 -> {
                    if (selectedFood == null) {
                        DiscoverScreen(onItemClick = { foodViewModel.selectFood(it) })
                    } else {
                        FoodDetailScreen(
                            foodItem = selectedFood!!,
                            onBack = { foodViewModel.clearSelection() }
                        )
                    }
                }
                2 -> Text("Shop Screen", modifier = Modifier.padding(16.dp))
                3 -> Text("Profile Screen", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

/**
 * A preview function to see the MainContent composable in Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContentPreview() {
    Foodshare_mobileTheme {
        MainContent()
    }
}
