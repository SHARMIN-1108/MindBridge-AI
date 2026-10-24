package com.example.mindbridge.ui.memorydetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.R
import com.example.mindbridge.ui.theme.MindbridgeTheme

val MemoryDetailsBackground = Color(0xFFFAF6EE)
val MemoryDetailsTealPrimary = Color(0xFF2E695F)
val MemoryDetailsTextDark = Color(0xFF1E2A38)
val MemoryDetailsTextGray = Color(0xFF5B6E80)
val MemoryDetailsCardBlueBg = Color(0xFFE4EFF5)
val MemoryDetailsMintBg = Color(0xFFCBE5DC)

@Composable
fun MemoryDetailsScreen(
    viewModel: MemoryDetailsViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MemoryDetailsBackground,
        bottomBar = {
            MemoryDetailsBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Memory Details" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = MemoryDetailsTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Memory Details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MemoryDetailsTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Photo Card showing memory details screenshot asset
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.memory_details_screen),
                    contentDescription = "Memory Photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = state.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MemoryDetailsTextDark
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Date
            Text(
                text = state.date,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MemoryDetailsTealPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle / Description
            Text(
                text = state.subtitle,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MemoryDetailsTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = state.description,
                fontSize = 14.sp,
                color = MemoryDetailsTextGray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Associated people
            Text(
                text = state.associatedPeople,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MemoryDetailsTextDark
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Audio Player Card (Mint bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MemoryDetailsMintBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
                                .background(Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🎤", fontSize = 16.sp, color = MemoryDetailsTealPrimary)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = state.voiceNoteTitle,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MemoryDetailsTextDark
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = state.voiceNoteSubtitle,
                                fontSize = 12.sp,
                                color = MemoryDetailsTextGray
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.White, CircleShape)
                            .clickable { viewModel.playVoiceNote() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "▷", fontSize = 16.sp, color = MemoryDetailsTealPrimary, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons
            Button(
                onClick = { viewModel.editMemory() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MemoryDetailsTealPrimary)
            ) {
                Text(
                    text = "Edit Memory",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Back to Memory Journey",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MemoryDetailsTealPrimary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MemoryDetailsBottomBar(selectedTab: Int) {
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
            MemoryDetailsBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            MemoryDetailsBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            MemoryDetailsBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            MemoryDetailsBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun MemoryDetailsBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) MemoryDetailsMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MemoryDetailsTealPrimary else MemoryDetailsTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MemoryDetailsScreenPreview() {
    MindbridgeTheme {
        MemoryDetailsScreen()
    }
}
