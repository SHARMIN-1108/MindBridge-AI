package com.example.mindbridge.ui.reminders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

val RemindersBackground = Color(0xFFFAF6EE)
val RemindersTealPrimary = Color(0xFF2E695F)
val RemindersTextDark = Color(0xFF1E2A38)
val RemindersTextGray = Color(0xFF5B6E80)
val RemindersMintBg = Color(0xFFCBE5DC)

@Composable
fun RemindersScreen(
    viewModel: RemindersViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = RemindersBackground,
        bottomBar = {
            RemindersBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "My Reminders" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = RemindersTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "My Reminders",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RemindersTextDark
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Date & Subtitle
            Text(
                text = state.dateText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = RemindersTextDark
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = state.subtitleText,
                fontSize = 13.sp,
                color = RemindersTextGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Section: Today
            Text(
                text = "Today",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RemindersTextDark
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.reminders.filter { it.isToday }.forEach { reminder ->
                    ReminderCard(reminder = reminder, onEditClick = { viewModel.editReminder(reminder.id) })
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Section: Upcoming
            Text(
                text = "Upcoming",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RemindersTextDark
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.reminders.filter { !it.isToday }.forEach { reminder ->
                    ReminderCard(reminder = reminder, onEditClick = { viewModel.editReminder(reminder.id) })
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Add Reminder Button
            Button(
                onClick = { viewModel.addReminder() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RemindersTealPrimary)
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
fun ReminderCard(
    reminder: ReminderItem,
    onEditClick: () -> Unit
) {
    val cardBg = if (reminder.isCompleted) RemindersMintBg else Color.White

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    val emoji = when (reminder.title) {
                        "Breakfast" -> "☕"
                        "Morning Walk" -> "👟"
                        "Lunch" -> "🍴"
                        "Call with Emma" -> "📞"
                        "Community appointment" -> "📅"
                        "Water the garden" -> "🌱"
                        else -> "📌"
                    }
                    Text(text = emoji, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = reminder.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = RemindersTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = reminder.timeOrDate,
                        fontSize = 12.sp,
                        color = RemindersTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onEditClick,
                modifier = Modifier.height(36.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (reminder.isCompleted) Color.White.copy(alpha = 0.8f) else Color(0xFFE4EFF5)),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
            ) {
                Text(text = "Edit", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = RemindersTextDark)
            }
        }
    }
}

@Composable
fun RemindersBottomBar(selectedTab: Int) {
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
            RemindersBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            RemindersBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            RemindersBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            RemindersBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun RemindersBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) RemindersMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) RemindersTealPrimary else RemindersTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RemindersScreenPreview() {
    MindbridgeTheme {
        RemindersScreen()
    }
}
