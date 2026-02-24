package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodshare_mobile.R

@Composable
fun RoleSelectionScreen(onFinished: (String) -> Unit) {
    var selectedRole by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background - Using the trash can image from your screenshot
        Image(
            painter = painterResource(id = R.drawable.bg_trash_splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                        startY = 400f
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.Center, // Centered the card like your screenshot
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // The Green Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF00891A))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome to\nFoodshare",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 36.sp
                )

                Spacer(Modifier.height(30.dp))

                RoleButton("I am a beneficiary", selectedRole == "beneficiary") {
                    selectedRole = "beneficiary"
                }
                Spacer(Modifier.height(12.dp))
                RoleButton("I am a hotel", selectedRole == "hotel") { selectedRole = "hotel" }
                Spacer(Modifier.height(12.dp))
                RoleButton("I am a guest", selectedRole == "guest") { selectedRole = "guest" }
            }

            Spacer(Modifier.height(30.dp))

            // Terms Row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = Color.White,
                        checkedColor = Color.Red
                    )
                )
                Text("I agree to the ", color = Color.White)
                Text(
                    "terms of service",
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Logic to show terms */ }
                )
            }

            Spacer(Modifier.height(24.dp))

            // Red Continue Button
            Button(
                onClick = { onFinished(selectedRole) },
                enabled = termsAccepted && selectedRole.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD50000),
                    disabledContainerColor = Color(0xFFD50000).copy(alpha = 0.5f)
                )
            ) {
                Text(
                    "Continue",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun RoleButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.White else Color(0xFFD9D9D9)
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Text(text, color = Color.Black, fontWeight = FontWeight.Medium, fontSize = 16.sp)
    }
}