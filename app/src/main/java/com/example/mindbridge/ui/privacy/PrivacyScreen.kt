package com.example.mindbridge.ui.privacy

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

val PrivacyBackground = Color(0xFFFAF6EE)
val PrivacyTealPrimary = Color(0xFF2E695F)
val PrivacyTextDark = Color(0xFF1E2A38)
val PrivacyTextGray = Color(0xFF5B6E80)
val PrivacyCardBlueBg = Color(0xFFE4EFF5)
val PrivacyMintBg = Color(0xFFCBE5DC)
val PrivacyYellowBg = Color(0xFFFFF5E4)

@Composable
fun PrivacyScreen(
    viewModel: PrivacyViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PrivacyBackground,
        bottomBar = {
            PrivacyBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Privacy and Sharing" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = PrivacyTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Privacy and Sharing",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrivacyTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Header & Subtitle
            Text(
                text = state.headerTitle,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = PrivacyTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = state.subtitle,
                fontSize = 13.sp,
                color = PrivacyTextGray,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Caregiver Connection Card
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
                                .background(PrivacyCardBlueBg, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "👥", fontSize = 16.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Caregiver connection",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrivacyTextDark
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = state.caregiverName,
                                fontSize = 12.sp,
                                color = PrivacyTextGray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { viewModel.reviewCaregiver() },
                        modifier = Modifier.height(36.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrivacyCardBlueBg),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
                    ) {
                        Text(text = "Review", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = PrivacyTextDark)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sharing Preferences Toggle Cards List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.sharingPrefs.forEach { pref ->
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
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = pref.title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrivacyTextDark
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = pref.subtitle,
                                    fontSize = 12.sp,
                                    color = PrivacyTextGray
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Switch(
                                checked = pref.isEnabled,
                                onCheckedChange = { viewModel.togglePref(pref.id) },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = PrivacyTealPrimary
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Descriptive text below cards
            Text(
                text = "You and your authorized caregiver can review which information is shared.",
                fontSize = 12.sp,
                color = PrivacyTextGray,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Review Permissions Button
            Button(
                onClick = { viewModel.reviewPermissions() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrivacyTealPrimary)
            ) {
                Text(text = "Review Permissions", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }

            // Disconnect Caregiver Confirmation Card (yellow bg) if shown
            if (state.showDisconnectConfirmation) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = PrivacyYellowBg),
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
                            color = PrivacyTextGray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Disconnect caregiver?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrivacyTextDark
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Emma would no longer receive shared updates. Confirm before changing this connection.",
                            fontSize = 13.sp,
                            color = PrivacyTextGray,
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { viewModel.keepConnected() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                            ) {
                                Text(text = "Keep connected", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = PrivacyTextDark)
                            }

                            Button(
                                onClick = { viewModel.disconnectCaregiver() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = PrivacyTealPrimary)
                            ) {
                                Text(text = "Disconnect", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
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
fun PrivacyBottomBar(selectedTab: Int) {
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
            PrivacyBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            PrivacyBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            PrivacyBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            PrivacyBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun PrivacyBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) PrivacyMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) PrivacyTealPrimary else PrivacyTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrivacyScreenPreview() {
    MindbridgeTheme {
        PrivacyScreen()
    }
}
