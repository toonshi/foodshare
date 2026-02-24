package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelsNearYouScreen(onBack: () -> Unit, onHotelClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Hotels Near You", color = Color(0xFF00891A), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(listOf("Top Hotel", "Shota Hotel", "Kwa Mathe")) { hotel ->
                HotelListCard(name = hotel, onClick = { onHotelClick(hotel) })
            }
        }
    }
}

@Composable
fun HotelListCard(name: String, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp).clickable { onClick() }) {
        Box(modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.White, RoundedCornerShape(12.dp)))
        Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(30.dp).background(Color.LightGray, CircleShape))
            Text(name, modifier = Modifier.padding(start = 8.dp).weight(1f), fontWeight = FontWeight.Bold)
            Text("⭐⭐⭐⭐⭐", fontSize = 12.sp)
        }
        Text("<1km away", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(start = 38.dp))
    }
}
