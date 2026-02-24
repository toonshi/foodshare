package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailScreen(foodItem: FoodItem, onBack: () -> Unit, onAddToCart: (FoodItem) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top Bar
        CenterAlignedTopAppBar(
            title = { Text(foodItem.name, fontWeight = FontWeight.Bold, fontSize = 18.sp) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
        )

        Column(modifier = Modifier.padding(16.dp)) {
            // Image Placeholder
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            ) {}

            Spacer(modifier = Modifier.height(24.dp))

            // Info Rows
            DetailRow(label = "Food", value = foodItem.name)
            DetailRow(label = "Hotel/restaurant", value = "Top Hotel", isLink = true)
            DetailRow(label = "Time left", value = "2:04:25")
            DetailRow(label = "Items left", value = "10")

            Spacer(modifier = Modifier.height(32.dp))

            // Add to Cart Button (Green)
            Button(
                onClick = { onAddToCart(foodItem) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00891A))
            ) {
                Text("Add to Cart", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, isLink: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Black, fontSize = 16.sp)
        Text(
            text = value,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = if (isLink) FontWeight.Bold else FontWeight.Normal,
            textDecoration = if (isLink) TextDecoration.Underline else TextDecoration.None
        )
    }
}
