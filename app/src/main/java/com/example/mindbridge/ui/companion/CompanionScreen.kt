package com.example.mindbridge.ui.companion

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val CompanionBackground = Color(0xFFFAF6EE)
val CompanionTealPrimary = Color(0xFF2E695F)
val CompanionTextDark = Color(0xFF1E2A38)
val CompanionTextGray = Color(0xFF5B6E80)
val CompanionBubbleBg = Color(0xFFFFFFFF)
val CompanionAvatarBg = Color(0xFFE4EFF5)

@Composable
fun CompanionScreen(
    viewModel: CompanionViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = CompanionBackground,
        bottomBar = {
            CompanionBottomBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar: Back arrow and "Your Companion" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = CompanionTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Your Companion",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CompanionTextDark
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Center Avatar Profile
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(CompanionAvatarBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CompanionAvatarFace(modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Greeting Speech Bubble Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CompanionBubbleBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = state.greeting,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CompanionTextDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.subtitle,
                        fontSize = 13.sp,
                        color = CompanionTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Suggestion Prompt Cards
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                PromptCard(
                    title = "Tell me a memory",
                    icon = "🖼️",
                    onClick = { viewModel.sendPrompt("Tell me a memory") }
                )
                PromptCard(
                    title = "Help me with today",
                    icon = "📅",
                    onClick = { viewModel.sendPrompt("Help me with today") }
                )
                PromptCard(
                    title = "I want to talk",
                    icon = "💬",
                    onClick = { viewModel.sendPrompt("I want to talk") }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Voice Interaction Button ("Tap to speak, if you'd like")
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(CompanionTealPrimary, CircleShape)
                        .clickable { viewModel.toggleListening() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🎙️", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tap to speak, if you'd like",
                    fontSize = 12.sp,
                    color = CompanionTextGray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Chat Input Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = state.messageInput,
                    onValueChange = { viewModel.onMessageChanged(it) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CompanionTealPrimary,
                        unfocusedBorderColor = Color(0xFFD8E2DC),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    placeholder = {
                        Text(text = "Type a message...", fontSize = 14.sp, color = CompanionTextGray)
                    },
                    singleLine = true
                )

                Button(
                    onClick = { viewModel.sendMessage() },
                    modifier = Modifier
                        .height(52.dp)
                        .width(80.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CompanionTealPrimary)
                ) {
                    Text(
                        text = "Send",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Footer Note
            Text(
                text = "Your AI companion for everyday support.",
                fontSize = 11.sp,
                color = CompanionTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PromptCard(
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CompanionBubbleBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = icon, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CompanionTextDark
                )
            }
            Text(text = "›", fontSize = 18.sp, color = CompanionTextGray)
        }
    }
}

@Composable
fun CompanionAvatarFace(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        // Leaf head
        val leafPath = Path().apply {
            moveTo(w * 0.5f, h * 0.05f)
            cubicTo(w * 0.9f, h * 0.05f, w * 0.95f, h * 0.5f, w * 0.5f, h * 0.9f)
            cubicTo(w * 0.05f, h * 0.5f, w * 0.1f, h * 0.05f, w * 0.5f, h * 0.05f)
            close()
        }
        drawPath(path = leafPath, color = CompanionTealPrimary)
    }
}

@Composable
fun CompanionBottomBar() {
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
            CompanionBottomBarItem(icon = "🏠", label = "Home", isSelected = true)
            CompanionBottomBarItem(icon = "🖼️", label = "Memories", isSelected = false)
            CompanionBottomBarItem(icon = "🧩", label = "Activities", isSelected = false)
            CompanionBottomBarItem(icon = "👤", label = "Profile", isSelected = false)
        }
    }
}

@Composable
fun CompanionBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) CompanionAvatarBg else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) CompanionTealPrimary else CompanionTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CompanionScreenPreview() {
    MindbridgeTheme {
        CompanionScreen()
    }
}
