package com.example.mindbridge.ui.profile

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

val ProfileBackground = Color(0xFFFAF6EE)
val ProfileTealPrimary = Color(0xFF2E695F)
val ProfileTextDark = Color(0xFF1E2A38)
val ProfileTextGray = Color(0xFF5B6E80)
val ProfileCardBlueBg = Color(0xFFE4EFF5)
val ProfileMintBg = Color(0xFFCBE5DC)

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.navigateToSettings) {
        onSettingsClick()
        viewModel.resetNavigation()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ProfileBackground,
        bottomBar = {
            ProfileBottomBar(selectedTab = state.selectedTab)
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
            // Top Bar: Back arrow and "My Profile" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = ProfileTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "My Profile",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Profile Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(ProfileCardBlueBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "👤", fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Profile photo placeholder",
                fontSize = 11.sp,
                color = ProfileTextGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = state.fullName,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileTextDark
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Preferred Name Card Container
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Preferred name · Used in greetings",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = ProfileTextGray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = state.preferredName,
                        onValueChange = { viewModel.onPreferredNameChanged(it) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ProfileTealPrimary,
                            unfocusedBorderColor = Color(0xFFD8E2DC),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Section Header: My app preferences
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "My app preferences",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileTextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Preference Card 1: Text & language
            PreferenceCard(
                title = "Text & language",
                subtitle = state.textPreference,
                icon = "T",
                onEditClick = { onSettingsClick() }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Preference Card 2: Sound and voice
            PreferenceCard(
                title = "Sound and voice",
                subtitle = state.soundPreference,
                icon = "🔊",
                onEditClick = { onSettingsClick() }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Preference Card 3: Family connection (mint bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ProfileMintBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = state.familyConnectionTitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ProfileTextDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.familyConnectionText,
                        fontSize = 13.sp,
                        color = ProfileTextGray,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons
            Button(
                onClick = { viewModel.editProfile() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ProfileTealPrimary)
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { viewModel.manageFamilyConnection() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Manage Family Connection",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ProfileTealPrimary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PreferenceCard(
    title: String,
    subtitle: String,
    icon: String,
    onEditClick: () -> Unit
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
                        .background(ProfileCardBlueBg, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = icon, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ProfileTealPrimary)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ProfileTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = ProfileTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onEditClick,
                modifier = Modifier.height(36.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ProfileCardBlueBg),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
            ) {
                Text(text = "Edit", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ProfileTextDark)
            }
        }
    }
}

@Composable
fun ProfileBottomBar(selectedTab: Int) {
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
            ProfileBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            ProfileBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            ProfileBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            ProfileBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun ProfileBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) ProfileMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) ProfileTealPrimary else ProfileTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MindbridgeTheme {
        ProfileScreen()
    }
}
