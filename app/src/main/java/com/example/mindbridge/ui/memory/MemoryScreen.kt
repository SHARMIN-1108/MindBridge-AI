package com.example.mindbridge.ui.memory

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

val MemoryBackground = Color(0xFFFAF6EE)
val MemoryTealPrimary = Color(0xFF2E695F)
val MemoryTextDark = Color(0xFF1E2A38)
val MemoryTextGray = Color(0xFF5B6E80)
val MemoryMintBg = Color(0xFFCBE5DC)
val MemoryCardBlueBg = Color(0xFFE4EFF5)

@Composable
fun MemoryScreen(
    viewModel: MemoryViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onAlbumClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.navigateToAlbum) {
        onAlbumClick()
        viewModel.resetNavigation()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MemoryBackground,
        bottomBar = {
            MemoryBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow, "My Memory Journey", album icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                        Text(text = "←", fontSize = 20.sp, color = MemoryTextDark, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "My Memory Journey",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MemoryTextDark
                    )
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, CircleShape)
                        .shadow(1.dp, CircleShape)
                        .clickable { viewModel.openAlbum() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🖼️", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Subtitle
            Text(
                text = state.subtitle,
                fontSize = 13.sp,
                color = MemoryTextGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Timeline Items List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.memories.forEach { memory ->
                    TimelineMemoryItem(memory = memory)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Add a Memory Button
            Button(
                onClick = { viewModel.addMemory() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MemoryTealPrimary)
            ) {
                Text(
                    text = "+ Add a Memory",
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
fun TimelineMemoryItem(memory: MemoryItem) {
    Column {
        // Date Header with timeline dot
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(MemoryTealPrimary, CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = memory.date,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MemoryTextDark
            )
        }

        // Memory Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Photo Thumbnail Box
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MemoryCardBlueBg),
                    contentAlignment = Alignment.Center
                ) {
                    val thumbEmoji = when (memory.id) {
                        "1" -> "🌳"
                        "2" -> "🎂"
                        "3" -> "🌊"
                        else -> "📸"
                    }
                    Text(text = thumbEmoji, fontSize = 32.sp)
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = memory.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MemoryTextDark
                    )
                    Text(
                        text = memory.description,
                        fontSize = 12.sp,
                        color = MemoryTextGray,
                        lineHeight = 16.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "🖼️", fontSize = 12.sp)
                        Text(text = "🎙️", fontSize = 12.sp)
                        Text(
                            text = memory.duration,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MemoryTextGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MemoryBottomBar(selectedTab: Int) {
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
            MemoryBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            MemoryBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            MemoryBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            MemoryBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun MemoryBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) MemoryMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MemoryTealPrimary else MemoryTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MemoryScreenPreview() {
    MindbridgeTheme {
        MemoryScreen()
    }
}
