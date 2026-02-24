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
import com.example.foodshare_mobile.ui.screens.CartScreen
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


// THIS IS THE CORRECTED FUNCTION
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(foodViewModel: FoodViewModel = viewModel()) {
    // This part holds the state for which item is selected in the Discover flow
    val selectedFood by foodViewModel.selectedFood.collectAsState()
    val cartItems by foodViewModel.cartItems.collectAsState()

    // --- CORRECTION 1: Only 3 items in the bottom nav list ---
    val bottomNavItems = listOf(
        BottomNavItem("Home", "home", R.drawable.ic_home, R.drawable.ic_home),
        BottomNavItem("Discover", "discover", R.drawable.ic_discovery, R.drawable.ic_discovery),
        BottomNavItem("Shop", "shop", R.drawable.ic_shopping_bag, R.drawable.ic_shopping_bag)
    )
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            // --- CORRECTION 2: The TopAppBar now has the Profile Icon ---
            TopAppBar(
                title = {
                    TextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Find a hotel/food") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF0F0F0),
                            unfocusedContainerColor = Color(0xFFF0F0F0),
                            disabledContainerColor = Color(0xFFF0F0F0),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(50.dp)
                    )
                },
                // The Profile icon is the navigationIcon on the left
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Navigate to Profile Screen */ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(28.dp) // Adjusted size
                        )
                    }
                },
                // The Notification icon is an action on the right
                actions = {
                    IconButton(onClick = { /* TODO: Navigate to Notifications */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                // This loop now correctly iterates over only the 3 items
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index },
                        label = null, // Your design doesn't show labels
                        icon = {
                            Icon(
                                painter = painterResource(id = item.selectedIcon),
                                contentDescription = item.title,
                                modifier = Modifier.size(26.dp)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            unselectedIconColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            // This logic correctly decides which screen to show
            when (selectedItemIndex) {
                0 -> HomeScreen(userName = "Toxic")
                1 -> {
                    if (selectedFood == null) {
                        DiscoverScreen(onItemClick = { foodViewModel.selectFood(it) })
                    } else {
                        FoodDetailScreen(
                            foodItem = selectedFood!!,
                            onBack = { foodViewModel.clearSelection() },
                            onAddToCart = {
                                foodViewModel.addToCart(it)
                                foodViewModel.clearSelection()
                            }
                        )
                    }
                }
                2 -> CartScreen(cartItems = cartItems)
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
