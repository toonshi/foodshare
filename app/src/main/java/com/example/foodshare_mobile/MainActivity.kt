package com.example.foodshare_mobile

import FoodShareApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.foodshare_mobile.ui.theme.Foodshare_mobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Foodshare_mobileTheme {
                // This calls the main controller in your other file
                FoodShareApp()
            }
        }
    }
}