package com.example.mindbridge.ui.helpfulinformation

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

val HelpInfoBackground = Color(0xFFFAF6EE)
val HelpInfoTealPrimary = Color(0xFF2E695F)
val HelpInfoTextDark = Color(0xFF1E2A38)
val HelpInfoTextGray = Color(0xFF5B6E80)
val HelpInfoCardWarmBg = Color(0xFFFFF9F0)
val HelpInfoMintBg = Color(0xFFE2F0EC)

@Composable
fun HelpfulInformationScreen(
    viewModel: HelpfulInformationViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = HelpInfoBackground,
        bottomBar = {
            HelpInfoBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Helpful Information" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = HelpInfoTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Helpful Information",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HelpInfoTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Header Title & Subtitle
            Text(
                text = "A few things that help me",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = HelpInfoTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "A simple card to review with someone you trust.",
                fontSize = 13.sp,
                color = HelpInfoTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Main Helpful Information Card (Warm yellow bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = HelpInfoCardWarmBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Example data - User-approved card example",
                        fontSize = 11.sp,
                        color = HelpInfoTealPrimary,
                        fontWeight = FontWeight.Medium
                    )

                    // Field 1
                    Column {
                        Text(text = "Please call me", fontSize = 12.sp, color = HelpInfoTextGray)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = state.pleaseCallMe, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = HelpInfoTextDark)
                    }

                    // Field 2
                    Column {
                        Text(text = "My caregiver contact", fontSize = 12.sp, color = HelpInfoTextGray)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = state.caregiverContact, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = HelpInfoTextDark)
                    }

                    // Field 3
                    Column {
                        Text(text = "Example phone number", fontSize = 12.sp, color = HelpInfoTextGray)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = state.phoneNumber, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = HelpInfoTextDark)
                    }

                    // Field 4
                    Column {
                        Text(text = "How I like to communicate", fontSize = 12.sp, color = HelpInfoTextGray)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = state.communicationStyle,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = HelpInfoTextDark,
                            lineHeight = 20.sp
                        )
                    }

                    // Field 5
                    Column {
                        Text(text = "Important notes", fontSize = 12.sp, color = HelpInfoTextGray)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = state.importantNotes,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = HelpInfoTextDark,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Notice Card (Mint bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HelpInfoMintBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🔒", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = state.noticeText,
                        fontSize = 12.sp,
                        color = HelpInfoTextDark,
                        lineHeight = 17.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Edit Information Button
            Button(
                onClick = { viewModel.editInformation() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HelpInfoTealPrimary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "✏️", fontSize = 16.sp)
                    Text(
                        text = "Edit Information",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun HelpInfoBottomBar(selectedTab: Int) {
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
            HelpInfoBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            HelpInfoBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            HelpInfoBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            HelpInfoBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun HelpInfoBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) HelpInfoMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) HelpInfoTealPrimary else HelpInfoTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HelpfulInformationScreenPreview() {
    MindbridgeTheme {
        HelpfulInformationScreen()
    }
}
