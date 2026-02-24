package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToAccountDetails: () -> Unit,
    onBack: () -> Unit
) {
    val greenColor = Color(0xFF00891A)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold, color = greenColor) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Header
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("Toxic Mlyriki", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { /* TODO: Edit Profile Action */ }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = greenColor, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Edit Profile", color = greenColor, textDecoration = TextDecoration.Underline)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Divider()

            // Menu Items
            ProfileMenuItem(title = "Account Details", onClick = onNavigateToAccountDetails)
            ProfileMenuItem(title = "KYC Status", value = "Verified", onClick = {})
            ProfileMenuItem(title = "Settings", onClick = {})

            Spacer(modifier = Modifier.height(24.dp))

            ProfileMenuItem(title = "Appearance", onClick = {})
            ProfileMenuItem(title = "Terms & Conditions", isUnderlined = true, onClick = {})
            ProfileMenuItem(title = "Log Out", onClick = {})
        }
    }
}

@Composable
private fun ProfileMenuItem(
    title: String,
    value: String? = null,
    isUnderlined: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            textDecoration = if (isUnderlined) TextDecoration.Underline else TextDecoration.None,
            color = Color.Black // Explicitly set color for consistency
        )
        if (value != null) {
            Text(text = value, color = Color.Gray)
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color.Gray
            )
        }
    }
    Divider()
}
