package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailScreen(hotelName: String, onBack: () -> Unit) {
    var selectedTab by remember { mutableStateOf("Available Food") }
    val greenColor = Color(0xFF00891A)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(hotelName, color = greenColor, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            // Big Hotel Image Placeholder
            Box(modifier = Modifier.fillMaxWidth().height(180.dp).background(Color.White, RoundedCornerShape(12.dp)))

            Text("We offer a wide range of food and hospitality services...",
                color = Color.Gray, modifier = Modifier.padding(vertical = 12.dp))

            // Info Row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp))
                Text("<1km away", fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp).weight(1f))
                Text("⭐⭐⭐⭐⭐ (10)", fontSize = 12.sp)
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // Tabs: Available Food | Expiring Soon
            Row(modifier = Modifier.fillMaxWidth()) {
                TabItem("Available Food", selectedTab == "Available Food") { selectedTab = "Available Food" }
                TabItem("Expiring Soon", selectedTab == "Expiring Soon") { selectedTab = "Expiring Soon" }
            }

            // Food List
            Spacer(Modifier.height(16.dp))
            FoodItemCard("Ugali Matumbo", "2 Hrs Left") // Placeholder call
        }
    }
}

@Composable
fun TabItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable { onClick() }.padding(end = 16.dp)) {
        Text(text, color = if (isSelected) Color.Black else Color.Gray, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
        if (isSelected) Box(Modifier.width(40.dp).height(3.dp).background(Color(0xFF00891A)))
    }
}

// Placeholder for FoodItemCard - similar to FoodCard from DiscoverScreen.kt
@Composable
fun FoodItemCard(name: String, timeLeft: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(60.dp).background(Color.LightGray, RoundedCornerShape(8.dp)))
            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(timeLeft, fontSize = 12.sp, color = Color.Gray)
            }
            // Add other elements if needed, like "Claim" button
        }
    }
}
