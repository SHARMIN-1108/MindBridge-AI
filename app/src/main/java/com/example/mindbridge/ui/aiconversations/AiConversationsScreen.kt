package com.example.mindbridge.ui.aiconversations

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val AiConvBackground = Color(0xFFFAF6EE)
val AiConvTealPrimary = Color(0xFF2E695F)
val AiConvTextDark = Color(0xFF1E2A38)
val AiConvTextGray = Color(0xFF5B6E80)
val AiConvCardBlueBg = Color(0xFFE4EFF5)
val AiConvMintBg = Color(0xFFCBE5DC)

@Composable
fun AiConversationsScreen(
    viewModel: AiConversationsViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = AiConvBackground,
        bottomBar = {
            AiConvBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Our Conversations" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = AiConvTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Our Conversations",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AiConvTextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "Little conversations, one moment at a time.",
                fontSize = 13.sp,
                color = AiConvTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // New Conversation Button
            Button(
                onClick = { viewModel.startNewConversation() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AiConvTealPrimary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "💬", fontSize = 16.sp)
                    Text(
                        text = "New Conversation",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Conversation Cards List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (state.conversations.isEmpty()) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = "When the list is empty", fontSize = 11.sp, color = AiConvTextGray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "No conversations saved yet",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = AiConvTextDark
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Start when you feel ready. Avoid sharing details you'd rather keep to yourself.",
                                fontSize = 12.sp,
                                color = AiConvTextGray,
                                lineHeight = 16.sp
                            )
                        }
                    }
                } else {
                    state.conversations.forEach { conversation ->
                        ConversationCard(
                            conversation = conversation,
                            onOpenClick = { viewModel.openConversation(conversation.id) },
                            onRemoveClick = { viewModel.removeConversation(conversation.id) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ConversationCard(
    conversation: ConversationItem,
    onOpenClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "💬", fontSize = 16.sp, color = AiConvTealPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = conversation.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = AiConvTextDark
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = conversation.dateTime,
                fontSize = 11.sp,
                color = AiConvTextGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = conversation.snippet,
                fontSize = 13.sp,
                color = AiConvTextDark,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Open conversation",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AiConvTealPrimary,
                    modifier = Modifier.clickable(onClick = onOpenClick)
                )

                Text(
                    text = "Remove",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = AiConvTextGray,
                    modifier = Modifier.clickable(onClick = onRemoveClick)
                )
            }
        }
    }
}

@Composable
fun AiConvBottomBar(selectedTab: Int) {
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
            AiConvBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            AiConvBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            AiConvBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            AiConvBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun AiConvBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) AiConvMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) AiConvTealPrimary else AiConvTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AiConversationsScreenPreview() {
    MindbridgeTheme {
        AiConversationsScreen()
    }
}
