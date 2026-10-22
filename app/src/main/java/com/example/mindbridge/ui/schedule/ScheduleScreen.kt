package com.example.mindbridge.ui.schedule

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val ScheduleBackground = Color(0xFFFAF6EE)
val ScheduleTealPrimary = Color(0xFF2E695F)
val ScheduleTextDark = Color(0xFF1E2A38)
val ScheduleTextGray = Color(0xFF5B6E80)
val ScheduleMintBg = Color(0xFFCBE5DC)

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ScheduleBackground,
        bottomBar = {
            ScheduleBottomBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow, "My Daily Routine", Calendar icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                        Text(text = "←", fontSize = 20.sp, color = ScheduleTextDark, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "My Daily Routine",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ScheduleTextDark
                    )
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, CircleShape)
                        .shadow(1.dp, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "📅", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Date & Subtitle
            Text(
                text = state.dateText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ScheduleTextDark
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = state.subtitleText,
                fontSize = 13.sp,
                color = ScheduleTextGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Routine Sections grouped by period
            val periods = listOf(
                RoutinePeriod.MORNING to "Morning",
                RoutinePeriod.AFTERNOON to "Afternoon",
                RoutinePeriod.EVENING to "Evening"
            )

            periods.forEach { (period, periodTitle) ->
                val periodItems = state.items.filter { it.period == period }
                if (periodItems.isNotEmpty()) {
                    RoutineSection(
                        title = periodTitle,
                        items = periodItems,
                        onItemClick = { viewModel.toggleItem(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Add Reminder Button
            Button(
                onClick = { viewModel.addReminder() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ScheduleTealPrimary)
            ) {
                Text(
                    text = "+ Add Reminder",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun RoutineSection(
    title: String,
    items: List<RoutineItem>,
    onItemClick: (String) -> Unit
) {
    Column {
        // Section Header with timeline icon/dot
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(ScheduleTealPrimary, CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ScheduleTextDark
            )
        }

        // Items list
        Column(
            modifier = Modifier.padding(start = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                RoutineCard(item = item, onClick = { onItemClick(item.id) })
            }
        }
    }
}

@Composable
fun RoutineCard(
    item: RoutineItem,
    onClick: () -> Unit
) {
    val cardBg = if (item.isCompleted) ScheduleMintBg else Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(cardBg)
            .border(
                width = if (item.isCompleted) 0.dp else 1.dp,
                color = if (item.isCompleted) Color.Transparent else ScheduleTealPrimary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icon for routine item
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    val emoji = when (item.title) {
                        "Breakfast" -> "☕"
                        "Morning Walk" -> "👟"
                        "Lunch" -> "🍴"
                        "Family Call" -> "📞"
                        "Bedtime" -> "🛏️"
                        else -> "📌"
                    }
                    Text(text = emoji, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = item.time,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = ScheduleTextGray
                        )
                        if (!item.subtitle.isNullOrEmpty()) {
                            Text(
                                text = " · ${item.subtitle}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = ScheduleTextGray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ScheduleTextDark
                    )
                }
            }

            // Checkbox indicator
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (item.isCompleted) ScheduleTealPrimary else Color.Transparent)
                    .border(
                        width = 1.5.dp,
                        color = if (item.isCompleted) ScheduleTealPrimary else ScheduleTextGray,
                        shape = RoundedCornerShape(6.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (item.isCompleted) {
                    Text(text = "✓", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ScheduleBottomBar() {
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
            ScheduleBottomBarItem(icon = "🏠", label = "Home", isSelected = true)
            ScheduleBottomBarItem(icon = "🖼️", label = "Memories", isSelected = false)
            ScheduleBottomBarItem(icon = "🧩", label = "Activities", isSelected = false)
            ScheduleBottomBarItem(icon = "👤", label = "Profile", isSelected = false)
        }
    }
}

@Composable
fun ScheduleBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) ScheduleMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) ScheduleTealPrimary else ScheduleTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScheduleScreenPreview() {
    MindbridgeTheme {
        ScheduleScreen()
    }
}
