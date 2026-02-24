package com.example.foodshare_mobile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodshare_mobile.ui.screens.FoodSplashScreen
import com.example.foodshare_mobile.ui.theme.Foodshare_mobileTheme

/**
 * The main entry point for the app's UI.
 * This composable manages the state to show either the onboarding
 * flow or the main app content.
 */
@Composable
fun FoodShareApp() {
    // State to track if the user has completed the onboarding screens.
    var showOnboarding by remember { mutableStateOf(true) }

    if (showOnboarding) {
        // Show the onboarding flow, which has its own internal state for pages.
        // When the user clicks "Continue" on the last page, onFinished is called.
        FoodSplashScreen(
            onFinished = {
                showOnboarding = false
            }
        )
    } else {
        // Once onboarding is finished, show the main content of the app.
        MainContent()
    }
}

/**
 * This is the placeholder for your main application screen, which will
 * eventually contain your Scaffold, Bottom Navigation Bar, and NavHost.
 */
@Composable
fun MainContent() {
    // A simple placeholder text for now.
    Text(text = "Welcome to the Dashboard!")
}

/**
 * A preview function to see the FoodShareApp composable in Android Studio.
 * It shows the starting state of the app (the onboarding screen).
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodShareAppPreview() {
    Foodshare_mobileTheme {
        FoodShareApp()
    }
}