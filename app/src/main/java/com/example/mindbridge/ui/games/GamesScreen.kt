package com.example.mindbridge.ui.games

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val GamesBackground = Color(0xFFFAF6EE)
val GamesTealPrimary = Color(0xFF2E695F)
val GamesTextDark = Color(0xFF1E2A38)
val GamesTextGray = Color(0xFF5B6E80)
val GamesCardBlueBg = Color(0xFFE4EFF5)

@Composable
fun GamesScreen(
    viewModel: GamesViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onProgressClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.navigateToProgress) {
        onProgressClick()
        viewModel.resetNavigation()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = GamesBackground,
        bottomBar = {
            GamesBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow, "Brain Games" title, progress icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                        Text(text = "←", fontSize = 20.sp, color = GamesTextDark, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Brain Games",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = GamesTextDark
                    )
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, CircleShape)
                        .shadow(1.dp, CircleShape)
                        .clickable { viewModel.openProgress() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "📊", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Header & Subtitle
            Text(
                text = "Let's Exercise Our Minds",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = GamesTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Enjoy a little activity at your own pace.",
                fontSize = 14.sp,
                color = GamesTextGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Difficulty Selector Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Difficulty",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = GamesTextGray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .background(GamesCardBlueBg)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val isEasy = state.difficulty == GameDifficulty.EASY
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isEasy) GamesTealPrimary else Color.Transparent)
                            .clickable { viewModel.setDifficulty(GameDifficulty.EASY) }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✓ Easy",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isEasy) Color.White else GamesTextDark
                        )
                    }

                    val isMedium = state.difficulty == GameDifficulty.MEDIUM
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isMedium) GamesTealPrimary else Color.Transparent)
                            .clickable { viewModel.setDifficulty(GameDifficulty.MEDIUM) }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Medium",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isMedium) Color.White else GamesTextDark
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Game Cards List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                state.games.forEach { game ->
                    GameCard(game = game, onPlayClick = { viewModel.playGame(game.id) })
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer note
            Text(
                text = "No timer. No rush. Just a moment for you.",
                fontSize = 12.sp,
                color = GamesTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun GameCard(
    game: GameItem,
    onPlayClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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
                // Thumbnail Box
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(GamesCardBlueBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = game.thumbnailEmoji, fontSize = 28.sp)
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = game.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = GamesTextDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = game.description,
                        fontSize = 12.sp,
                        color = GamesTextGray,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Play Button
            Button(
                onClick = onPlayClick,
                modifier = Modifier.height(40.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GamesTealPrimary),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Text(text = "▶ Play", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }
    }
}

@Composable
fun GamesBottomBar(selectedTab: Int) {
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
            GamesBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            GamesBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            GamesBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            GamesBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun GamesBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Color(0xFFCBE5DC).copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) GamesTealPrimary else GamesTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GamesScreenPreview() {
    MindbridgeTheme {
        GamesScreen()
    }
}
