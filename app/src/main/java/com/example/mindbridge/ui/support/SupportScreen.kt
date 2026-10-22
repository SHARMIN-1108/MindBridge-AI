package com.example.mindbridge.ui.support

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val SupportBackground = Color(0xFFFAF6EE)
val SupportTealPrimary = Color(0xFF2E695F)
val SupportTextDark = Color(0xFF1E2A38)
val SupportTextGray = Color(0xFF5B6E80)
val SupportMintBg = Color(0xFFCBE5DC)
val SupportYellowBg = Color(0xFFFFF5E4)

@Composable
fun SupportScreen(
    viewModel: SupportViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = SupportBackground,
        bottomBar = {
            SupportBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Help and Support" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = SupportTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Help and Support",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = SupportTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Header Icon
            Text(text = "🤝", fontSize = 28.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // Main Header & Subtitle
            Text(
                text = state.headerTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = SupportTextDark,
                lineHeight = 30.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = state.subtitle,
                fontSize = 14.sp,
                color = SupportTextGray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons
            Button(
                onClick = { viewModel.callCaregiver() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SupportTealPrimary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "📞", fontSize = 16.sp)
                    Text(text = "Call My Caregiver", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { viewModel.callEmergencyServices() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SupportTealPrimary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "🚨", fontSize = 16.sp)
                    Text(text = "Call Emergency Services", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { viewModel.viewContacts() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "📖", fontSize = 16.sp, color = SupportTealPrimary)
                    Text(text = "View Important Contacts", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = SupportTealPrimary)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Emergency Contact Card (white bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Emergency contact · Example data",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = SupportTextGray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = state.emergencyContact.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = SupportTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = state.emergencyContact.phone,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SupportTealPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = state.emergencyContact.note,
                        fontSize = 12.sp,
                        color = SupportTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Notice Card (yellow bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SupportYellowBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = state.noticeTitle,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SupportTextDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.noticeText,
                        fontSize = 12.sp,
                        color = SupportTextGray,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SupportBottomBar(selectedTab: Int) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SupportBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            SupportBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            SupportBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            SupportBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun SupportBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) SupportMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) SupportTealPrimary else SupportTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SupportScreenPreview() {
    MindbridgeTheme {
        SupportScreen()
    }
}
