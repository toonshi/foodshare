package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.clickable

// Data model for the food items
data class FoodItem(
    val name: String,
    val timeLeft: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(onItemClick: (FoodItem) -> Unit) {
    // Sample data based on your screenshot
    val foodList = listOf(
        FoodItem("Kamande", "2 Hrs Left"),
        FoodItem("Ugali Matumbo", "3 Hrs Left"),
        FoodItem("Uji Mrenda", "2 Hrs Left"),
        FoodItem("Githeri Special", "1 Hr Left")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light grey background like screenshot
    ) {
        // Custom Header (matching the screenshot exactly)
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Top Picks",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Handle back if needed */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent
            )
        )

        // Vertical List of Food Cards
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(foodList) { item ->
                Box(modifier = Modifier.clickable { onItemClick(item) }) {
                    FoodCard(item)
                }
            }
        }
    }
}

@Composable
fun FoodCard(item: FoodItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Image Placeholder (The large white box)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            // Future Image goes here
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Text Info Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
            Text(
                text = item.timeLeft,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
    }
}
