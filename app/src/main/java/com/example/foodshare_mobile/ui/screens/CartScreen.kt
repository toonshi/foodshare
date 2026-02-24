package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodshare_mobile.ui.viewmodels.FoodViewModel

@Composable
fun CartScreen(
    viewModel: FoodViewModel
) {
    val pendingItems by viewModel.pendingCart.collectAsState()
    val completeItems by viewModel.completeOrders.collectAsState()
    var isPendingSelected by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFEDEDED))) {
        Text(
            "My Cart",
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Toggle Row (Matches your screenshot exactly)
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TabButton(
                text = "Pending Orders",
                isSelected = isPendingSelected,
                modifier = Modifier.weight(1f)
            ) { isPendingSelected = true }

            Spacer(modifier = Modifier.width(8.dp)) // Space between buttons

            TabButton(
                text = "Complete Orders",
                isSelected = !isPendingSelected,
                modifier = Modifier.weight(1f),
                isOutlined = true
            ) { isPendingSelected = false }
        }


        if (isPendingSelected) {
            // --- PENDING VIEW ---
            if (pendingItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No pending orders.", color = Color.Gray, fontSize = 16.sp)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(pendingItems.keys.toList()) { item -> // Iterate over keys of the map
                        val quantity = pendingItems[item] ?: 0
                        PendingItemRow(
                            item, quantity,
                            onIncrease = { viewModel.updateQuantity(item, true) },
                            onDecrease = { viewModel.updateQuantity(item, false) }
                        )
                    }
                }
            }
            // BIG GREEN CLAIM BUTTON
            if (pendingItems.isNotEmpty()) {
                Button(
                    onClick = { viewModel.finalizeOrder() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00891A))
                ) {
                    Text("Claim Now", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        } else {
            // --- COMPLETE VIEW ---
            if (completeItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No completed orders.", color = Color.Gray, fontSize = 16.sp)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(completeItems) { item ->
                        CompleteItemRow(item)
                    }
                }
            }
        }
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    isOutlined: Boolean = false,
    onClick: () -> Unit
) {
    if (isOutlined) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.height(42.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, if (isSelected) Color(0xFF00891A) else Color.Transparent),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = if (isSelected) Color.Black else Color(0xFF757575)
            )
        ) {
            Text(text, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
    } else {
        Button(
            onClick = onClick,
            modifier = modifier.height(42.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Color(0xFFD6D6D6) else Color.Transparent,
                contentColor = if (isSelected) Color.Black else Color(0xFF757575)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(text, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun PendingItemRow(item: FoodItem, quantity: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp), // Matched to design
        shape = RoundedCornerShape(12.dp) // Matched to design
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(item.name, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(item.timeLeft, fontSize = 12.sp, color = Color.Gray)
            }
            // Quantity Selector: - 1 +
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(20.dp))
                    .padding(horizontal = 4.dp) // Adjusted horizontal padding
            ) {
                Text(
                    "-",
                    modifier = Modifier
                        .clickable { onDecrease() }
                        .padding(horizontal = 4.dp, vertical = 8.dp), // Adjusted padding
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp // Matched to design
                )
                Text(
                    "$quantity",
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp), // Adjusted padding
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp // Matched to design
                )
                Text(
                    "+",
                    modifier = Modifier
                        .clickable { onIncrease() }
                        .padding(horizontal = 4.dp, vertical = 8.dp), // Adjusted padding
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp // Matched to design
                )
            }
        }
    }
}

@Composable
fun CompleteItemRow(item: FoodItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp), // Matched to design
        shape = RoundedCornerShape(12.dp) // Matched to design
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(item.name, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(item.timeLeft, fontSize = 12.sp, color = Color.Gray)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Text(
                    "Track Order",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
