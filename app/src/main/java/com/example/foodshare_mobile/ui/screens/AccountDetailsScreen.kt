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
import androidx.compose.ui.unit.dp

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
            // Assuming ProfileMenuItem is a utility composable defined elsewhere or will be defined.
            // If it's specific to ProfileScreen, this call might be problematic.
            // For now, I'll assume it's a general utility and let it be.
            ProfileMenuItem(title = "Advance", onClick = { /* Navigate to Advance */ })
        }
    }
}

// Assuming DetailRow is a general utility function; if not, it should be defined here.
// Re-using the DetailRow from FoodDetailScreen.kt
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
