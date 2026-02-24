import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodshare_mobile.ui.screens.AccountDetailsScreen
import com.example.foodshare_mobile.ui.screens.NotificationDetailScreen
import com.example.foodshare_mobile.ui.screens.NotificationScreen
import com.example.foodshare_mobile.ui.screens.HotelsNearYouScreen
import com.example.foodshare_mobile.ui.screens.HotelDetailScreen
import com.example.foodshare_mobile.ui.screens.ProfileScreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
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

// This is the top-level controller, which is already correct
@Composable
fun FoodShareApp() {
    val navController = rememberNavController()
    val foodViewModel: FoodViewModel = viewModel()

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            FoodSplashScreen(onFinished = { navController.navigate("role_selection") })
        }
        composable("role_selection") {
            RoleSelectionScreen(onFinished = { navController.navigate("main_app") { popUpTo("onboarding") { inclusive = true } } })
        }
        composable("main_app") {
            MainContent(
                foodViewModel = foodViewModel,
                onNavigateToProfile = { navController.navigate("profile") },
                onNavigateToNotifications = { navController.navigate("notifications") }
            )
        }
        composable("profile") {
            ProfileScreen(
                onNavigateToAccountDetails = { navController.navigate("account_details") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("account_details") {
            AccountDetailsScreen(onBack = { navController.popBackStack() })
        }
        // Notification Routes
        composable("notifications") {
            NotificationScreen(
                onBack = { navController.popBackStack() },
                onNavigateToDetail = { navController.navigate("notification_detail") }
            )
        }
        composable("notification_detail") {
            NotificationDetailScreen(onBack = { navController.popBackStack() })
        }
        // Hotel Routes
        composable("hotels_near_you") {
            HotelsNearYouScreen(
                onBack = { navController.popBackStack() },
                onHotelClick = { hotelName -> navController.navigate("hotel_detail/$hotelName") }
            )
        }
        composable("hotel_detail/{hotelName}") { backStackEntry ->
            val hotelName = backStackEntry.arguments?.getString("hotelName") ?: "Hotel"
            HotelDetailScreen(
                hotelName = hotelName,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    foodViewModel: FoodViewModel,
    onNavigateToProfile: () -> Unit, // New parameter to handle navigation
    onNavigateToNotifications: () -> Unit // New parameter for notifications
) {
    // This part holds the state for which item is selected in the Discover flow
    val selectedFood by foodViewModel.selectedFood.collectAsState()

    // --- CORRECTION 1: Only 3 items in the bottom nav list ---
    val bottomNavItems = listOf(
        BottomNavItem("Home", "home", com.example.foodshare_mobile.R.drawable.ic_home, com.example.foodshare_mobile.R.drawable.ic_home),
        BottomNavItem("Discover", "discover", com.example.foodshare_mobile.R.drawable.ic_discovery, com.example.foodshare_mobile.R.drawable.ic_discovery),
        BottomNavItem("Shop", "shop", com.example.foodshare_mobile.R.drawable.ic_shopping_bag, com.example.foodshare_mobile.R.drawable.ic_shopping_bag)
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
                    IconButton(onClick = onNavigateToProfile) { // THIS IS THE CHANGE
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(28.dp) // Adjusted size
                        )
                    }
                },
                // The Notification icon is an action on the right
                actions = {
                    IconButton(onClick = onNavigateToNotifications) { // Updated logic
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
                2 -> CartScreen(viewModel = foodViewModel)
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
        MainContent(foodViewModel = viewModel(), onNavigateToProfile = {}, onNavigateToNotifications = {})
    }
}
