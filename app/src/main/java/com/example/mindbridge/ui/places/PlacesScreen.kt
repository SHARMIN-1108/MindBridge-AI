package com.example.mindbridge.ui.places

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

val PlacesBackground = Color(0xFFFAF6EE)
val PlacesTealPrimary = Color(0xFF2E695F)
val PlacesTextDark = Color(0xFF1E2A38)
val PlacesTextGray = Color(0xFF5B6E80)
val PlacesCardBlueBg = Color(0xFFE4EFF5)
val PlacesMintBg = Color(0xFFCBE5DC)

@Composable
fun PlacesScreen(
    viewModel: PlacesViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PlacesBackground,
        bottomBar = {
            PlacesBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "My Familiar Places" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = PlacesTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "My Familiar Places",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PlacesTextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = state.subtitle,
                fontSize = 13.sp,
                color = PlacesTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Top Map Container Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PlacesCardBlueBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Map preview placeholder box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFD8E2DC)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🗺️ Map Preview", fontSize = 14.sp, color = PlacesTextGray, fontWeight = FontWeight.Medium)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Two quick selection buttons inside map card
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MapQuickButton(
                            title = "Home",
                            icon = "🏠",
                            modifier = Modifier.weight(1f)
                        )
                        MapQuickButton(
                            title = "Family Home",
                            icon = "🏠",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Caption below map
            Text(
                text = "Conceptual map only. No live location or tracking.",
                fontSize = 11.sp,
                color = PlacesTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Familiar Places Cards List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                state.places.forEach { place ->
                    PlaceCard(place = place, onDirectionsClick = { viewModel.getDirections(place.id) })
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Call My Caregiver Button
            Button(
                onClick = { viewModel.callCaregiver() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PlacesTealPrimary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "📞", fontSize = 16.sp)
                    Text(
                        text = "Call My Caregiver",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Footer note
            Text(
                text = "Directions and calling are example controls, not live services.",
                fontSize = 11.sp,
                color = PlacesTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MapQuickButton(
    title: String,
    icon: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = icon, fontSize = 16.sp, color = PlacesTealPrimary)
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PlacesTextDark
            )
        }
    }
}

@Composable
fun PlaceCard(
    place: PlaceItem,
    onDirectionsClick: () -> Unit
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
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(PlacesCardBlueBg, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = place.iconEmoji, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = place.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PlacesTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = place.description,
                        fontSize = 12.sp,
                        color = PlacesTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Directions",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PlacesTealPrimary,
                modifier = Modifier.clickable(onClick = onDirectionsClick)
            )
        }
    }
}

@Composable
fun PlacesBottomBar(selectedTab: Int) {
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
            PlacesBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            PlacesBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            PlacesBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            PlacesBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun PlacesBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) PlacesMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) PlacesTealPrimary else PlacesTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlacesScreenPreview() {
    MindbridgeTheme {
        PlacesScreen()
    }
}
