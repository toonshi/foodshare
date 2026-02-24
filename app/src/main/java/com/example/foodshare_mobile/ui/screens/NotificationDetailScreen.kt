package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationDetailScreen(onBack: () -> Unit) {
    val greenColor = Color(0xFF00891A)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Details", color = greenColor, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(24.dp)) {
            Text("Your Order has been delivered. Visit your delivery address to pick it.", color = Color.Gray)

            Spacer(Modifier.height(24.dp))

            DetailInfoRow("Order No:", "561234")
            DetailInfoRow("Food:", "Ugali Fish")
            DetailInfoRow("Hotel/Restaurant:", "Top Hotel")
            DetailInfoRow("Delivery Time:", "12:00PM")

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenColor)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Rate The Experience", fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun DetailInfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(label, modifier = Modifier.weight(1f), fontWeight = FontWeight.Medium, color = Color.Black)
        Text(value, color = Color.DarkGray)
    }
}
