package com.example.mindbridge.ui.caregiver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val CaregiverBackground = Color(0xFFFAF6EE)
val CaregiverTealPrimary = Color(0xFF2E695F)
val CaregiverTextDark = Color(0xFF1E2A38)
val CaregiverTextGray = Color(0xFF5B6E80)
val CaregiverCardBlueBg = Color(0xFFE4EFF5)
val CaregiverMintBg = Color(0xFFCBE5DC)

@Composable
fun CaregiverScreen(
    viewModel: CaregiverViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onViewMemoriesClick: () -> Unit = {},
    onManageRemindersClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onGamesClick: () -> Unit = {},
    onScheduleClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.navigateToSupport) {
        onSupportClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToMemories) {
        onViewMemoriesClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToReminders) {
        onManageRemindersClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToGames) {
        onGamesClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToSchedule) {
        onScheduleClick()
        viewModel.resetNavigation()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = CaregiverBackground,
        bottomBar = {
            CaregiverBottomBar(
                selectedTab = state.selectedTab,
                onHomeClick = onHomeClick,
                onMemoriesClick = onViewMemoriesClick,
                onActivitiesClick = onGamesClick,
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Caregiver Overview" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = CaregiverTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Caregiver Overview",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CaregiverTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Profile Banner Card (blue bg) - Clicking opens My Profile
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onProfileClick),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CaregiverCardBlueBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👤", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = state.memberName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = CaregiverTextDark
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = state.memberRole,
                            fontSize = 12.sp,
                            color = CaregiverTealPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Section Header: Recent app moments
            Text(
                text = "Recent app moments",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CaregiverTextDark
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Moment 1: Enjoyed a word game -> Opens Games
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.openGames() },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🧩", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Enjoyed a word game",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = CaregiverTextDark
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Today · 9:15 AM",
                            fontSize = 12.sp,
                            color = CaregiverTextGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Moment 2: Breakfast completed -> Opens Schedule
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.openSchedule() },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CaregiverMintBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "✓", fontSize = 18.sp, color = CaregiverTealPrimary, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Breakfast completed",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = CaregiverTextDark
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Today · 8:00 AM · 1 of 5 routines",
                            fontSize = 12.sp,
                            color = CaregiverTextGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Moment 3: Coming up & new memories -> Opens Memories
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.openMemories() },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Coming up & new memories",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = CaregiverTextDark
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "10:00 AM · Morning Walk\n4:00 PM · Call with Emma\n\nNew: Family Picnic voice note\nAdded by Emma · 12 October",
                        fontSize = 13.sp,
                        color = CaregiverTextGray,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons
            Button(
                onClick = onViewMemoriesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CaregiverTealPrimary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "🖼️", fontSize = 16.sp)
                    Text(text = "View Memories", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onManageRemindersClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
            ) {
                Text(text = "Manage Reminders", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = CaregiverTealPrimary)
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { viewModel.openSupport() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "📞", fontSize = 16.sp, color = CaregiverTealPrimary)
                    Text(text = "Contact Family Member", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = CaregiverTealPrimary)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Footer note
            Text(
                text = state.footerText,
                fontSize = 11.sp,
                color = CaregiverTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CaregiverBottomBar(
    selectedTab: Int,
    onHomeClick: () -> Unit,
    onMemoriesClick: () -> Unit,
    onActivitiesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
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
            CaregiverBottomBarItem(
                icon = "🏠",
                label = "Home",
                isSelected = selectedTab == 0,
                onClick = onHomeClick
            )
            CaregiverBottomBarItem(
                icon = "🖼️",
                label = "Memories",
                isSelected = selectedTab == 1,
                onClick = onMemoriesClick
            )
            CaregiverBottomBarItem(
                icon = "🧩",
                label = "Activities",
                isSelected = selectedTab == 2,
                onClick = onActivitiesClick
            )
            CaregiverBottomBarItem(
                icon = "👤",
                label = "Profile",
                isSelected = selectedTab == 3,
                onClick = onProfileClick
            )
        }
    }
}

@Composable
fun CaregiverBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) CaregiverMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) CaregiverTealPrimary else CaregiverTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CaregiverScreenPreview() {
    MindbridgeTheme {
        CaregiverScreen()
    }
}
