package com.example.mindbridge.ui.familyupdates

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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

val FamilyUpdatesBackground = Color(0xFFFAF6EE)
val FamilyUpdatesTealPrimary = Color(0xFF2E695F)
val FamilyUpdatesTextDark = Color(0xFF1E2A38)
val FamilyUpdatesTextGray = Color(0xFF5B6E80)
val FamilyUpdatesCardBlueBg = Color(0xFFE4EFF5)
val FamilyUpdatesMintBg = Color(0xFFCBE5DC)
val FamilyUpdatesYellowBg = Color(0xFFFFF5E4)

@Composable
fun FamilyUpdatesScreen(
    viewModel: FamilyUpdatesViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = FamilyUpdatesBackground,
        bottomBar = {
            FamilyUpdatesBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Family Updates" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = FamilyUpdatesTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Family Updates",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = FamilyUpdatesTextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle & Feed Header
            Text(
                text = state.subtitle,
                fontSize = 13.sp,
                color = FamilyUpdatesTextGray
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = state.feedHeader,
                fontSize = 11.sp,
                color = FamilyUpdatesTealPrimary,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Feed Items List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                state.feedItems.forEach { item ->
                    when (item) {
                        is FeedItem.PhotoUpdate -> {
                            PhotoUpdateCard(update = item, onOpenClick = { viewModel.openUpdate(item.id) })
                        }
                        is FeedItem.AudioUpdate -> {
                            SimpleUpdateCard(title = item.title, details = item.details, icon = item.icon, onOpenClick = { viewModel.openUpdate(item.id) })
                        }
                        is FeedItem.CallUpdate -> {
                            SimpleUpdateCard(title = item.title, details = item.details, icon = item.icon, onOpenClick = { viewModel.openUpdate(item.id) })
                        }
                    }
                }

                if (state.showEmptyState) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = FamilyUpdatesYellowBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "When the list is empty",
                                fontSize = 11.sp,
                                color = FamilyUpdatesTextGray,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "No new updates right now",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = FamilyUpdatesTextDark
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "When an approved update is added, it will appear here. Enjoy your day, Margaret.",
                                fontSize = 12.sp,
                                color = FamilyUpdatesTextGray,
                                lineHeight = 17.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PhotoUpdateCard(
    update: FeedItem.PhotoUpdate,
    onOpenClick: () -> Unit
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
            // Image Preview box using family update asset
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFD8E2DC))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.family_update_screen),
                    contentDescription = update.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = update.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = FamilyUpdatesTextDark
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = update.senderAndDate,
                fontSize = 12.sp,
                color = FamilyUpdatesTextGray
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = update.message,
                fontSize = 14.sp,
                color = FamilyUpdatesTextDark,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Open Update",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = FamilyUpdatesTealPrimary,
                modifier = Modifier.clickable(onClick = onOpenClick)
            )
        }
    }
}

@Composable
fun SimpleUpdateCard(
    title: String,
    details: String,
    icon: String,
    onOpenClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                Text(text = icon, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = FamilyUpdatesTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = details,
                        fontSize = 12.sp,
                        color = FamilyUpdatesTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Open",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = FamilyUpdatesTealPrimary,
                modifier = Modifier.clickable(onClick = onOpenClick)
            )
        }
    }
}

@Composable
fun FamilyUpdatesBottomBar(selectedTab: Int) {
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
            FamilyUpdatesBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            FamilyUpdatesBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            FamilyUpdatesBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            FamilyUpdatesBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun FamilyUpdatesBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) FamilyUpdatesMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) FamilyUpdatesTealPrimary else FamilyUpdatesTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FamilyUpdatesScreenPreview() {
    MindbridgeTheme {
        FamilyUpdatesScreen()
    }
}
