package com.example.mindbridge.ui.voice

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val VoiceBackground = Color(0xFFFAF6EE)
val VoiceTealPrimary = Color(0xFF2E695F)
val VoiceTextDark = Color(0xFF1E2A38)
val VoiceTextGray = Color(0xFF5B6E80)
val VoiceMintBg = Color(0xFFCBE5DC)
val VoiceCardBlueBg = Color(0xFFE4EFF5)
val VoiceYellowBg = Color(0xFFFFF5E4)

@Composable
fun VoiceScreen(
    viewModel: VoiceViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = VoiceBackground,
        bottomBar = {
            VoiceBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Memories in Their Voice" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = VoiceTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Memories in Their Voice",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = VoiceTextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = state.subtitle,
                fontSize = 13.sp,
                color = VoiceTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Top Banner Card: Record a Memory
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.recordMemory() },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = VoiceCardBlueBg),
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
                            .size(40.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🎙️", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "Record a Memory",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = VoiceTextDark
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Example recording control",
                            fontSize = 12.sp,
                            color = VoiceTextGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Section Header: Saved voices
            Text(
                text = "Saved voices",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = VoiceTextDark
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Saved Voices List Card
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
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.records.forEachIndexed { index, record ->
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = record.title,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = VoiceTextDark
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "${record.author} · ${record.date}",
                                        fontSize = 12.sp,
                                        color = VoiceTextGray
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = record.duration,
                                        fontSize = 11.sp,
                                        color = VoiceTextGray
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Button(
                                        onClick = { viewModel.togglePlay(record.id) },
                                        modifier = Modifier.height(36.dp),
                                        shape = RoundedCornerShape(10.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = VoiceCardBlueBg),
                                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
                                    ) {
                                        Text(text = "▶ Play", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = VoiceTextDark)
                                    }
                                    Text(
                                        text = "Delete",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = VoiceTealPrimary,
                                        modifier = Modifier.clickable { viewModel.requestDelete(record.id) }
                                    )
                                }
                            }

                            if (index < state.records.size - 1) {
                                Spacer(modifier = Modifier.height(12.dp))
                                HorizontalDivider(color = Color(0xFFF0F4F2), thickness = 1.dp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Playback Player Card (mint bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = VoiceMintBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Playback example - Emma",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = VoiceTextDark
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(VoiceTealPrimary, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "⏸", color = Color.White, fontSize = 16.sp)
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color.White.copy(alpha = 0.7f))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(state.playbackProgress)
                                        .fillMaxHeight()
                                        .background(VoiceTealPrimary)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = state.playbackTimeText,
                                fontSize = 11.sp,
                                color = VoiceTextGray
                            )
                        }
                    }
                }
            }

            // Confirmation Example Card (yellow bg) if requested
            if (state.showDeleteConfirmationForId != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = VoiceYellowBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Confirmation example",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = VoiceTextGray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Delete David's recording?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = VoiceTextDark
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "This removes \"A Day by the Sea\".",
                            fontSize = 13.sp,
                            color = VoiceTextGray
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { viewModel.cancelDelete() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                            ) {
                                Text(text = "Keep recording", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = VoiceTextDark)
                            }

                            Button(
                                onClick = { viewModel.confirmDelete() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = VoiceTealPrimary)
                            ) {
                                Text(text = "Delete", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun VoiceBottomBar(selectedTab: Int) {
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
            VoiceBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            VoiceBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            VoiceBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            VoiceBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun VoiceBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) VoiceMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) VoiceTealPrimary else VoiceTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VoiceScreenPreview() {
    MindbridgeTheme {
        VoiceScreen()
    }
}
