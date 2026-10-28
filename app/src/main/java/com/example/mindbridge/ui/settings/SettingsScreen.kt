package com.example.mindbridge.ui.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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

val SettingsBackground = Color(0xFFFAF6EE)
val SettingsTealPrimary = Color(0xFF2E695F)
val SettingsTextDark = Color(0xFF1E2A38)
val SettingsTextGray = Color(0xFF5B6E80)
val SettingsCardBlueBg = Color(0xFFE4EFF5)
val SettingsMintBg = Color(0xFFCBE5DC)
val SettingsYellowBg = Color(0xFFFFF5E4)

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = SettingsBackground,
        bottomBar = {
            SettingsBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Settings" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = SettingsTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Settings",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = SettingsTextDark
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Settings Items List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.settings.forEach { setting ->
                    SettingCard(setting = setting, onOpenClick = { viewModel.openSetting(setting.id) })
                }
            }

            // Logout Confirmation Card (yellow bg) if shown
            if (state.showLogoutConfirmation) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SettingsYellowBg),
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
                            color = SettingsTextGray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Log out of MindBridge AI?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = SettingsTextDark
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "You can log in again when you're ready.",
                            fontSize = 13.sp,
                            color = SettingsTextGray
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { viewModel.stayHere() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                            ) {
                                Text(text = "Stay here", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = SettingsTextDark)
                            }

                            Button(
                                onClick = {
                                    viewModel.logout()
                                    onLogoutClick()
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SettingsTealPrimary)
                            ) {
                                Text(text = "Log Out", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
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
fun SettingCard(
    setting: SettingItem,
    onOpenClick: () -> Unit
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
                        .background(SettingsCardBlueBg, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = setting.iconEmoji, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SettingsTealPrimary)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = setting.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = SettingsTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = setting.subtitle,
                        fontSize = 12.sp,
                        color = SettingsTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onOpenClick,
                modifier = Modifier.height(36.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SettingsCardBlueBg),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
            ) {
                Text(text = "Open", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = SettingsTextDark)
            }
        }
    }
}

@Composable
fun SettingsBottomBar(selectedTab: Int) {
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
            SettingsBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            SettingsBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            SettingsBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            SettingsBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun SettingsBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) SettingsMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) SettingsTealPrimary else SettingsTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    MindbridgeTheme {
        SettingsScreen()
    }
}
