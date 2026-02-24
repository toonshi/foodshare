package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(onBack: () -> Unit, onNavigateToDetail: () -> Unit) {
    var selectedTab by remember { mutableStateOf("All") }
    val greenColor = Color(0xFF00891A)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notifications", color = greenColor, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            // All / Unread Tabs
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "All",
                    modifier = Modifier.clickable { selectedTab = "All" }.padding(horizontal = 20.dp),
                    fontWeight = if (selectedTab == "All") FontWeight.Bold else FontWeight.Normal,
                    textDecoration = if (selectedTab == "All") TextDecoration.Underline else null
                )
                Text(
                    "Unread",
                    modifier = Modifier.clickable { selectedTab = "Unread" }.padding(horizontal = 20.dp),
                    fontWeight = if (selectedTab == "Unread") FontWeight.Bold else FontWeight.Normal,
                    textDecoration = if (selectedTab == "Unread") TextDecoration.Underline else null
                )
            }

            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(listOf("Order Delivered", "Order Created")) { title ->
                    NotificationCard(title, onNavigateToDetail)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("12:00PM", color = Color.Gray, fontSize = 12.sp)
            }
            Text(
                "Your Order No:561234 (Ugali Fish) has been...",
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 1
            )
        }
    }
}
