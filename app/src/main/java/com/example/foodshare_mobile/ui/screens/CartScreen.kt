package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
    var isPendingSelected by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "My Cart",
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Toggle Buttons
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Pending Orders Button
            Button(
                onClick = { isPendingSelected = true },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f).height(40.dp).padding(horizontal = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPendingSelected) Color(0xFFD9D9D9) else Color.Transparent,
                    contentColor = Color.Gray
                )
            ) { Text("Pending Orders", fontSize = 12.sp) }

            // Complete Orders Button
            OutlinedButton(
                onClick = { isPendingSelected = false },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f).height(40.dp).padding(horizontal = 4.dp),
                border = BorderStroke(1.dp, if (!isPendingSelected) Color(0xFF00891A) else Color.Gray),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (!isPendingSelected) Color.Black else Color.Gray
                )
            ) { Text("Complete Orders", fontSize = 12.sp) }
        }

        // List of Cart Items
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
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
        modifier = Modifier.fillMaxWidth().height(80.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image Placeholder (White Box)
            Box(modifier = Modifier.size(60.dp).background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)))

            Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(item.timeLeft, fontSize = 11.sp, color = Color.Gray)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Track Order", fontSize = 12.sp)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }
    }
}
