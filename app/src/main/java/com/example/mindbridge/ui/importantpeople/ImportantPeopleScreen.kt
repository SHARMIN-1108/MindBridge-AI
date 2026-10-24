package com.example.mindbridge.ui.importantpeople

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val ImpPeopleBackground = Color(0xFFFAF6EE)
val ImpPeopleTealPrimary = Color(0xFF2E695F)
val ImpPeopleTextDark = Color(0xFF1E2A38)
val ImpPeopleTextGray = Color(0xFF5B6E80)
val ImpPeopleCardBlueBg = Color(0xFFE4EFF5)
val ImpPeopleMintBg = Color(0xFFCBE5DC)
val ImpPeopleYellowBg = Color(0xFFFFF5E4)

@Composable
fun ImportantPeopleScreen(
    viewModel: ImportantPeopleViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ImpPeopleBackground,
        bottomBar = {
            ImpPeopleBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "People in My Life" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = ImpPeopleTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "People in My Life",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ImpPeopleTextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "The people who make everyday life special.",
                fontSize = 13.sp,
                color = ImpPeopleTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // People Cards List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.people.forEach { person ->
                    PersonCard(
                        person = person,
                        onEditClick = { viewModel.editPerson(person.id) },
                        onRemoveClick = { viewModel.removePerson(person.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add a Person Button
            Button(
                onClick = { viewModel.addPerson() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ImpPeopleTealPrimary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "+", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(
                        text = "Add a Person",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Confirmation Example Card (Yellow bg)
            if (state.showConfirmation) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = ImpPeopleYellowBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Confirmation example",
                            fontSize = 11.sp,
                            color = ImpPeopleTextGray,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Remove ${state.confirmationName} from this list?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ImpPeopleTextDark
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Your saved memories will stay here.",
                            fontSize = 12.sp,
                            color = ImpPeopleTextGray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { viewModel.dismissConfirmation() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFD8E2DC)),
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                            ) {
                                Text(
                                    text = "Keep person",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = ImpPeopleTextDark
                                )
                            }

                            Button(
                                onClick = { viewModel.removePerson("3") },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ImpPeopleTealPrimary)
                            ) {
                                Text(
                                    text = "Remove",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Footer note
            Text(
                text = "Photo placeholders · Example people",
                fontSize = 11.sp,
                color = ImpPeopleTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PersonCard(
    person: PersonItem,
    onEditClick: () -> Unit,
    onRemoveClick: () -> Unit
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
                        .size(42.dp)
                        .background(ImpPeopleCardBlueBg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "👤", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = person.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ImpPeopleTextDark
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = person.relationship,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = ImpPeopleTextDark
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = person.notes,
                        fontSize = 12.sp,
                        color = ImpPeopleTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Edit",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ImpPeopleTealPrimary,
                    modifier = Modifier.clickable(onClick = onEditClick)
                )
                Text(
                    text = "Remove",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = ImpPeopleTextGray,
                    modifier = Modifier.clickable(onClick = onRemoveClick)
                )
            }
        }
    }
}

@Composable
fun ImpPeopleBottomBar(selectedTab: Int) {
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
            ImpPeopleBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            ImpPeopleBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            ImpPeopleBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            ImpPeopleBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun ImpPeopleBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) ImpPeopleMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) ImpPeopleTealPrimary else ImpPeopleTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImportantPeopleScreenPreview() {
    MindbridgeTheme {
        ImportantPeopleScreen()
    }
}
