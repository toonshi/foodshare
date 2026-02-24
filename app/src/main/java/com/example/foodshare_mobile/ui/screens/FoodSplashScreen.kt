package com.example.foodshare_mobile.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodshare_mobile.R
import com.example.foodshare_mobile.ui.theme.Foodshare_mobileTheme

@Composable
fun FoodSplashScreen(onFinished: () -> Unit) {
    // 0 = Start, 1 = Hotel Info, 2 = Beneficiary Info, 3 = Goal Info
    var currentPage by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Dynamic Background Image
        val backgroundImage = when (currentPage) {
            0 -> R.drawable.bg_food_splash
            1 -> R.drawable.bg_human_splash
            2 -> R.drawable.bg_phone_splash
            else -> R.drawable.bg_trash_splash // This is now screen 3 (the 4th screen)
        }

        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 2. Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                        startY = 600f
                    )
                )
        )

        // 3. Content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentPage) {
                0 -> {
                    // --- SCREEN 1: INTRO (Food Splash) ---
                    Text(
                        "Introducing",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "FoodShare",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "A blockchain backed food management system. Reduce wasting food by managing excess food. Hotels link up with beneficiaries who need the food.",
                        color = Color.White.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(32.dp))
                    RedButton(text = "Get Started") { currentPage = 1 }
                }

                1 -> {
                    // --- SCREEN 2: HOTEL STRUGGLE (Human Splash) ---
                    Text(
                        text = "Are you a hotel and you are struggling with surplus food?",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Create an account on our platform and share the surplus food to get tokenized. You still get to earn income from donating this food.",
                        color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(40.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        BackButton { currentPage = 0 }
                        NextButton { currentPage = 2 }
                    }
                }

                2 -> {
                    // --- SCREEN 3: BENEFICIARY (Phone Splash) --- (This is the new screen)
                    Text(
                        text = "Do You struggle with affording a meal daily?",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Create an account as a beneficiary and claim surplus food from your nearest hotel/restaurant.",
                        color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(40.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        BackButton { currentPage = 1 }
                        NextButton { currentPage = 3 }
                    }
                }

                3 -> {
                    // --- SCREEN 4: GOAL (Trash Splash) ---
                    Text(
                        text = "Our goal is to reduce food wastage in the society.",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "We connect beneficiaries with hotels and hotels get tokenized once they donate this food.",
                        color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(40.dp))
                    RedButton(text = "Continue") { onFinished() }
                }
            }
        }
    }
}

// Reusable Red Button
@Composable
fun RedButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(55.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD50000))
    ) {
        Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

// Reusable Back Button
@Composable
fun RowScope.BackButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.White),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
    ) {
        Text("Back")
    }
}

// Reusable Next Button
@Composable
fun RowScope.NextButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.White),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
    ) {
        Text("Next")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodSplashScreenPreview() {
    Foodshare_mobileTheme {
        FoodSplashScreen(onFinished = {})
    }
}