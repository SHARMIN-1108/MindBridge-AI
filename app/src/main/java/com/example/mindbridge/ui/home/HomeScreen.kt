package com.example.mindbridge.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val HomeBackground = Color(0xFFFAF6EE)
val HomeTealPrimary = Color(0xFF2E695F)
val HomeTextDark = Color(0xFF1E2A38)
val HomeTextGray = Color(0xFF5B6E80)
val CardBlueBg = Color(0xFFE4EFF5)
val CardMintBg = Color(0xFFCBE5DC)
val CardYellowBg = Color(0xFFFFF5E4)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onScheduleClick: () -> Unit = {},
    onCompanionClick: () -> Unit = {},
    onGamesClick: () -> Unit = {},
    onMemoryClick: () -> Unit = {},
    onRemindersClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.navigateToSchedule) {
        onScheduleClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToCompanion) {
        onCompanionClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToGames) {
        onGamesClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToMemory) {
        onMemoryClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToReminders) {
        onRemindersClick()
        viewModel.resetNavigation()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            HomeBottomBar(
                selectedTab = state.selectedTab,
                onTabSelected = { viewModel.selectTab(it) }
            )
        },
        containerColor = HomeBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: MindBridge AI logo + notification bell
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .shadow(1.dp, RoundedCornerShape(8.dp))
                            .background(Color.White, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        HomeMiniLogo(modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "MindBridge AI",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = HomeTextDark
                    )
                }

                // Notification Bell Icon Button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, CircleShape)
                        .shadow(1.dp, CircleShape)
                        .clickable { viewModel.openReminders() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🔔", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Date Header
            Text(
                text = state.dateText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = HomeTextGray
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Greeting
            Text(
                text = "${state.greeting}, ${state.userName}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = HomeTextDark
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Subtitle
            Text(
                text = "Let's take today one moment at a time.",
                fontSize = 14.sp,
                color = HomeTextGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 2x2 Grid of Feature Cards
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FeatureCard(
                        title = "AI Companion",
                        icon = "💬",
                        backgroundColor = CardBlueBg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.openCompanion() }
                    )
                    FeatureCard(
                        title = "Memory Journey",
                        icon = "🖼️",
                        backgroundColor = CardMintBg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.openMemory() }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FeatureCard(
                        title = "Brain Games",
                        icon = "🧩",
                        backgroundColor = CardYellowBg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.openGames() }
                    )
                    FeatureCard(
                        title = "Daily Schedule",
                        icon = "📅",
                        backgroundColor = CardBlueBg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.openSchedule() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Today's Reminders Section Header
            Text(
                text = "Today's Reminders",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = HomeTextDark
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Reminders Container Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    state.reminders.forEach { reminder ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.toggleReminder(reminder.id) },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Checkbox icon box
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (reminder.isCompleted) HomeTealPrimary else Color.Transparent)
                                        .border(
                                            width = 1.5.dp,
                                            color = if (reminder.isCompleted) HomeTealPrimary else HomeTextGray,
                                            shape = RoundedCornerShape(6.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (reminder.isCompleted) {
                                        Text(text = "✓", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = reminder.title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = HomeTextDark
                                )
                            }
                            Text(
                                text = reminder.time,
                                fontSize = 12.sp,
                                color = HomeTextGray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Daily Motivation / Progress Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardMintBg)
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "A lovely start to your day",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = HomeTextDark
                        )
                        Text(
                            text = "${state.completedCount} of ${state.totalCount}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = HomeTextGray
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    // Progress bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color.White.copy(alpha = 0.6f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(state.completedCount.toFloat() / state.totalCount.toFloat())
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(3.dp))
                                .background(HomeTealPrimary)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    icon: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = icon, fontSize = 20.sp)
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = HomeTextDark
            )
        }
    }
}

@Composable
fun HomeBottomBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit
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
            BottomBarItem(
                icon = "🏠",
                label = "Home",
                isSelected = selectedTab == HomeTab.HOME,
                onClick = { onTabSelected(HomeTab.HOME) }
            )
            BottomBarItem(
                icon = "🖼️",
                label = "Memories",
                isSelected = selectedTab == HomeTab.MEMORIES,
                onClick = { onTabSelected(HomeTab.MEMORIES) }
            )
            BottomBarItem(
                icon = "🧩",
                label = "Activities",
                isSelected = selectedTab == HomeTab.ACTIVITIES,
                onClick = { onTabSelected(HomeTab.ACTIVITIES) }
            )
            BottomBarItem(
                icon = "👤",
                label = "Profile",
                isSelected = selectedTab == HomeTab.PROFILE,
                onClick = { onTabSelected(HomeTab.PROFILE) }
            )
        }
    }
}

@Composable
fun BottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) CardMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) HomeTealPrimary else HomeTextGray
        )
    }
}

@Composable
fun HomeMiniLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val leafPath = Path().apply {
            moveTo(w * 0.50f, h * 0.12f)
            cubicTo(w * 0.88f, h * 0.12f, w * 0.95f, h * 0.58f, w * 0.50f, h * 0.90f)
            cubicTo(w * 0.05f, h * 0.58f, w * 0.12f, h * 0.12f, w * 0.50f, h * 0.12f)
            close()
        }
        drawPath(path = leafPath, color = HomeTealPrimary)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MindbridgeTheme {
        HomeScreen()
    }
}
