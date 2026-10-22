package com.example.mindbridge.ui.album

import androidx.compose.foundation.BorderStroke
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

val AlbumBackground = Color(0xFFFAF6EE)
val AlbumTealPrimary = Color(0xFF2E695F)
val AlbumTextDark = Color(0xFF1E2A38)
val AlbumTextGray = Color(0xFF5B6E80)
val AlbumMintBg = Color(0xFFCBE5DC)
val AlbumCardBlueBg = Color(0xFFE4EFF5)

@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onVoiceClick: () -> Unit = {},
    onPlacesClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.navigateToVoice) {
        onVoiceClick()
        viewModel.resetNavigation()
    }
    if (state.navigateToPlaces) {
        onPlacesClick()
        viewModel.resetNavigation()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = AlbumBackground,
        bottomBar = {
            AlbumBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow, "My Family Album", voice icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                        Text(text = "←", fontSize = 20.sp, color = AlbumTextDark, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "My Family Album",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AlbumTextDark
                    )
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, CircleShape)
                        .shadow(1.dp, CircleShape)
                        .clickable { viewModel.openVoice() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🎙️", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = state.subtitle,
                fontSize = 13.sp,
                color = AlbumTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Category Filter Pills Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CategoryPill(
                    title = "My Family",
                    isSelected = state.selectedCategory == AlbumCategory.MY_FAMILY,
                    onClick = { viewModel.selectCategory(AlbumCategory.MY_FAMILY) }
                )
                CategoryPill(
                    title = "Special Days",
                    isSelected = state.selectedCategory == AlbumCategory.SPECIAL_DAYS,
                    onClick = { viewModel.selectCategory(AlbumCategory.SPECIAL_DAYS) }
                )
                CategoryPill(
                    title = "Places I Love",
                    isSelected = state.selectedCategory == AlbumCategory.PLACES_I_LOVE,
                    onClick = { viewModel.selectCategory(AlbumCategory.PLACES_I_LOVE) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Photo Grid (2 columns)
            val rows = state.photos.chunked(2)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rows.forEach { rowPhotos ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowPhotos.forEach { photo ->
                            Box(modifier = Modifier.weight(1f)) {
                                AlbumPhotoCard(
                                    photo = photo,
                                    onClick = { viewModel.selectPhoto(photo.id) }
                                )
                            }
                        }
                        if (rowPhotos.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Selected Photo Name Edit Section
            Text(
                text = "Person name - selected photo",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = AlbumTextDark
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.selectedPersonName,
                    onValueChange = { viewModel.onNameChanged(it) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AlbumTealPrimary,
                        unfocusedBorderColor = Color(0xFFD8E2DC),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    leadingIcon = {
                        Text(text = "👤", fontSize = 16.sp)
                    },
                    singleLine = true
                )

                Button(
                    onClick = { viewModel.saveName() },
                    modifier = Modifier.height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, AlbumTealPrimary)
                ) {
                    Text(
                        text = "Save name",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AlbumTealPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Add Photo Button
            Button(
                onClick = { viewModel.addPhoto() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AlbumTealPrimary)
            ) {
                Text(
                    text = "+ Add Photo",
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
fun CategoryPill(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(if (isSelected) AlbumTealPrimary else Color.White)
            .border(
                width = if (isSelected) 0.dp else 1.dp,
                color = if (isSelected) Color.Transparent else Color(0xFFD8E2DC),
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) Color.White else AlbumTextDark
        )
    }
}

@Composable
fun AlbumPhotoCard(
    photo: AlbumPhoto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Photo thumbnail box with checkmark badge if selected
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(AlbumCardBlueBg),
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = photo.emojiThumb, fontSize = 36.sp)
                }

                if (photo.isSelected) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                            .background(AlbumTealPrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "✓", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = photo.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = AlbumTextDark
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = photo.date,
                    fontSize = 11.sp,
                    color = AlbumTextGray
                )
            }
        }
    }
}

@Composable
fun AlbumBottomBar(selectedTab: Int) {
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
            AlbumBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            AlbumBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            AlbumBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            AlbumBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun AlbumBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) AlbumMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) AlbumTealPrimary else AlbumTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlbumScreenPreview() {
    MindbridgeTheme {
        AlbumScreen()
    }
}
