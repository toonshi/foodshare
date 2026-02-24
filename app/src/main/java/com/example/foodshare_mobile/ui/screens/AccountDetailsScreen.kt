package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodshare_mobile.ui.screens.DetailRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Account Details", fontWeight = FontWeight.Bold, color = Color(0xFF00891A)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* More options */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DetailRow(label = "Name", value = "Toxic Mlyriki")
            DetailRow(label = "Date of Birth", value = "06-05-1990")
            DetailRow(label = "Nationality", value = "Kenyan")
            DetailRow(label = "ID Number", value = "07202612")
            DetailRow(label = "Phone Number", value = "+254-720261224")
            DetailRow(label = "Email", value = "toxicmlyriki @mmail.com")
            ProfileMenuItem(title = "Advance", onClick = { /* Navigate to Advance */ })
        }
    }
}
