package com.example.mindbridge.ui.dailycheckin

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val CheckInBackground = Color(0xFFFAF6EE)
val CheckInTealPrimary = Color(0xFF2E695F)
val CheckInTextDark = Color(0xFF1E2A38)
val CheckInTextGray = Color(0xFF5B6E80)
val CheckInCardBlueBg = Color(0xFFE4EFF5)
val CheckInMintBg = Color(0xFFCBE5DC)

@Composable
fun DailyCheckInScreen(
    viewModel: DailyCheckInViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = CheckInBackground,
        bottomBar = {
            CheckInBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "How Are You Feeling Today?" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = CheckInTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "How Are You Feeling Today?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CheckInTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Header Title & Subtitle
            Text(
                text = "Take a gentle moment.",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = CheckInTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "There's no right or wrong answer. Choose what feels closest to you.",
                fontSize = 13.sp,
                color = CheckInTextGray,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mood options list
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.moods.forEach { mood ->
                    val isSelected = mood.id == state.selectedMoodId
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.selectMood(mood.id) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) CheckInMintBg.copy(alpha = 0.5f) else Color.White
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = mood.icon, fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = mood.title,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CheckInTextDark
                                )
                            }

                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(22.dp)
                                        .background(CheckInTealPrimary, RoundedCornerShape(11.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "✓", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Note Text Field
            Text(
                text = "A short note · Optional",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = CheckInTextDark
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.noteText,
                onValueChange = { viewModel.onNoteChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CheckInTealPrimary,
                    unfocusedBorderColor = Color(0xFFD8E2DC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Save Check-In Button
            Button(
                onClick = { viewModel.saveCheckIn() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CheckInTealPrimary)
            ) {
                Text(
                    text = "Save Check-In",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Skip for now link
            Text(
                text = "Skip for now",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = CheckInTealPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.skipForNow() }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CheckInBottomBar(selectedTab: Int) {
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
            CheckInBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            CheckInBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            CheckInBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            CheckInBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun CheckInBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) CheckInMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) CheckInTealPrimary else CheckInTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DailyCheckInScreenPreview() {
    MindbridgeTheme {
        DailyCheckInScreen()
    }
}
