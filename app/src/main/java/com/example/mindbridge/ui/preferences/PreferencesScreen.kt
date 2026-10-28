package com.example.mindbridge.ui.preferences

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

val PrefBackground = Color(0xFFFAF6EE)
val PrefTealPrimary = Color(0xFF2E695F)
val PrefTextDark = Color(0xFF1E2A38)
val PrefTextGray = Color(0xFF5B6E80)
val PrefCardBlueBg = Color(0xFFE4EFF5)
val PrefMintBg = Color(0xFFCBE5DC)

@Composable
fun PreferencesScreen(
    viewModel: PreferencesViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PrefBackground,
        bottomBar = {
            PrefBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Things I Like" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = PrefTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Things I Like",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrefTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Info Card (Blue bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = PrefCardBlueBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "A little more like you",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrefTextDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Your choices help personalize suggestions and conversation topics. They aren't a medical assessment.",
                        fontSize = 12.sp,
                        color = PrefTextGray,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Preferences Items List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.preferences.forEach { preference ->
                    PreferenceCard(preference = preference, onEditClick = { viewModel.editPreference(preference.id) })
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Save My Preferences Button
            Button(
                onClick = { viewModel.savePreferences() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrefTealPrimary)
            ) {
                Text(
                    text = "Save My Preferences",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Footer note
            Text(
                text = "You can change your favorites anytime.",
                fontSize = 11.sp,
                color = PrefTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PreferenceCard(
    preference: PreferenceItem,
    onEditClick: () -> Unit
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
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(PrefCardBlueBg, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = preference.icon, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = preference.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrefTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = preference.subtitle,
                        fontSize = 12.sp,
                        color = PrefTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Edit",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PrefTealPrimary,
                modifier = Modifier.clickable(onClick = onEditClick)
            )
        }
    }
}

@Composable
fun PrefBottomBar(selectedTab: Int) {
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
            PrefBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            PrefBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            PrefBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            PrefBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun PrefBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) PrefMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) PrefTealPrimary else PrefTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreferencesScreenPreview() {
    MindbridgeTheme {
        PreferencesScreen()
    }
}
