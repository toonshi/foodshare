package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
fun CartScreen(cartItems: List<FoodItem>) {
    // This state tracks which tab is active
    var isPendingSelected by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            // Using a more accurate light grey from your screenshot
            .background(Color(0xFFEDEDED)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "My Cart" Header
        Text(
            text = "My Cart",
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Toggle Buttons Row
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Pending Orders Tab
            Button(
                onClick = { isPendingSelected = true },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .padding(end = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    // Matches that muted grey in your screenshot
                    containerColor = if (isPendingSelected) Color(0xFFD6D6D6) else Color.Transparent,
                    contentColor = Color(0xFF757575)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp) // Added to match the screenshot
            ) {
                Text("Pending Orders", fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }

            // Complete Orders Tab
            Surface(
                onClick = { isPendingSelected = false },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .padding(start = 4.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.Transparent,
                // The green border from your screenshot
                border = BorderStroke(
                    width = 1.dp,
                    color = if (!isPendingSelected) Color(0xFF00891A) else Color.Transparent
                )
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        "Complete Orders",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (!isPendingSelected) Color.Black else Color(0xFF757575)
                    )
                }
            }
        }

        // The List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(cartItems) { item ->
                CartItemRow(item)
            }
        }
    }
}

@Composable
fun CartItemRow(item: FoodItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp), // Adjusted height to match screenshot proportion
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Square image placeholder
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(8.dp))
            )

            // Text Info
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.timeLeft,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // Track Order Action
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Text(
                    text = "Track Order",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
