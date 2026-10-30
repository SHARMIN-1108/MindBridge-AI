package com.example.mindbridge.ui.addmemory

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val AddMemoryBackground = Color(0xFFFAF6EE)
val AddMemoryTealPrimary = Color(0xFF2E695F)
val AddMemoryTextDark = Color(0xFF1E2A38)
val AddMemoryTextGray = Color(0xFF5B6E80)
val AddMemoryCardBlueBg = Color(0xFFE4EFF5)
val AddMemoryMintBg = Color(0xFFCBE5DC)

@Composable
fun AddMemoryScreen(
    viewModel: AddMemoryViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onSaveSuccess: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isSavedSuccess) {
        onSaveSuccess()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = AddMemoryBackground,
        bottomBar = {
            AddMemoryBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "Add a New Memory" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = AddMemoryTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Add a New Memory",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AddMemoryTextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "A photo, a few words, or a familiar voice. Start with what you remember.",
                fontSize = 13.sp,
                color = AddMemoryTextGray,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Field 1: Memory title
            Text(text = "Memory title", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AddMemoryTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.title,
                onValueChange = { viewModel.onTitleChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g. A morning in the garden") },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AddMemoryTealPrimary,
                    unfocusedBorderColor = Color(0xFFD8E2DC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = state.titleHint, fontSize = 11.sp, color = AddMemoryTextGray)

            Spacer(modifier = Modifier.height(14.dp))

            // Field 2: Date
            Text(text = "Date", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AddMemoryTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.date,
                onValueChange = { viewModel.onDateChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g. 13 October 2026") },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AddMemoryTealPrimary,
                    unfocusedBorderColor = Color(0xFFD8E2DC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Field 3: Short description
            Text(text = "Short description", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AddMemoryTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.description,
                onValueChange = { viewModel.onDescriptionChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                placeholder = { Text("What made this moment special?") },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AddMemoryTealPrimary,
                    unfocusedBorderColor = Color(0xFFD8E2DC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Field 4: Associated people
            Text(text = "Associated people", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AddMemoryTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.associatedPeople,
                onValueChange = { viewModel.onAssociatedPeopleChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g. Margaret, Emma") },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AddMemoryTealPrimary,
                    unfocusedBorderColor = Color(0xFFD8E2DC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Use names familiar to you.", fontSize = 11.sp, color = AddMemoryTextGray)

            Spacer(modifier = Modifier.height(16.dp))

            // Media Action Buttons
            OutlinedButton(
                onClick = { viewModel.addPhoto() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "🖼️", fontSize = 16.sp, color = AddMemoryTealPrimary)
                    Text(text = "Add Photo", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = AddMemoryTealPrimary)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { viewModel.recordVoiceNote() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "🎙️", fontSize = 16.sp, color = AddMemoryTealPrimary)
                    Text(text = "Record Voice Note", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = AddMemoryTealPrimary)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.mediaStatus,
                fontSize = 11.sp,
                color = AddMemoryTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            state.errorMessage?.let { error ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Bottom Actions: Cancel & Save Memory
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AddMemoryTextDark
                    )
                }

                Button(
                    onClick = { viewModel.saveMemory() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    enabled = !state.isLoading,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AddMemoryTealPrimary)
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text(
                            text = "Save Memory",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun AddMemoryBottomBar(selectedTab: Int) {
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
            AddMemoryBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            AddMemoryBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            AddMemoryBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            AddMemoryBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun AddMemoryBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) AddMemoryMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) AddMemoryTealPrimary else AddMemoryTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddMemoryScreenPreview() {
    MindbridgeTheme {
        AddMemoryScreen()
    }
}
