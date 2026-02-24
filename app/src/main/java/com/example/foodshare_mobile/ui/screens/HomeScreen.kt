package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(userName: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Top "Good evening" Text
        item {
            Text(
                text = "Good evening $userName",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Top Picks Section
        item {
            SectionHeader(title = "Top Picks")
            Spacer(modifier = Modifier.height(8.dp))
            // This is a placeholder for your horizontal scrolling cards
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.4f))
            ) {
                // Content of your card, like "Kamande" and "2 Hrs Left" would go here
            }
        }

        // Hotels Near You Section
        item {
            SectionHeader(title = "Hotels Near You")
            Spacer(modifier = Modifier.height(8.dp))
            // Placeholder for the second list of cards
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.4f))
            ) {
                // Content for "Top Hotel", stars, distance, etc. goes here
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "See all", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
    }
}