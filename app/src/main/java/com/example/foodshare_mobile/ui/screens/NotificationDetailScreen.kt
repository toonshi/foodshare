package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationDetailScreen(onBack: () -> Unit) {
    val greenColor = Color(0xFF00891A)
    // State to control if the popup is visible
    var showRatingDialog by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.height(24.dp))

            DetailInfoRow("Order No:", "561234")
            DetailInfoRow("Food:", "Ugali Fish")
            DetailInfoRow("Hotel/Restaurant:", "Top Hotel")
            DetailInfoRow("Delivery Time:", "12:00PM")

            Spacer(modifier = Modifier.height(40.dp))

            // Clicking this button now shows the dialog
            Button(
                onClick = { showRatingDialog = true },
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

    // The Rating Popup Logic
    if (showRatingDialog) {
        RatingPopup(
            onDismiss = { showRatingDialog = false },
            onSubmit = { rating ->
                // Handle the rating submission here
                showRatingDialog = false
            }
        )
    }
}

@Composable
fun RatingPopup(onDismiss: () -> Unit, onSubmit: (Int) -> Unit) {
    var rating by remember { mutableIntStateOf(5) } // Default to 5 stars

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF00891A)) // Matching green
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rate The Experience",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Stars Row
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (i <= rating) Color(0xFFFFB400) else Color.White.copy(alpha = 0.5f),
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { rating = i }
                                .padding(2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Submit Button
                Button(
                    onClick = { onSubmit(rating) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Submit", fontWeight = FontWeight.Bold)
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
