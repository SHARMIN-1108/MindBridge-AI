package com.example.mindbridge.ui.about

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val AboutBackground = Color(0xFFFAF6EE)
val AboutTealPrimary = Color(0xFF2E695F)
val AboutTextDark = Color(0xFF1E2A38)
val AboutTextGray = Color(0xFF5B6E80)
val AboutCardBlueBg = Color(0xFFE4EFF5)
val AboutMintBg = Color(0xFFCBE5DC)

@Composable
fun AboutScreen(
    viewModel: AboutViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = AboutBackground,
        bottomBar = {
            AboutBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar: Back arrow and "About MindBridge AI" title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = AboutTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "About MindBridge AI",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AboutTextDark
                )
            }

            // Top Navigation Sub-bar matching image design
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SubTabItem(icon = "🏠", label = "Home", isSelected = false)
                        SubTabItem(icon = "🖼️", label = "Memories", isSelected = false)
                        SubTabItem(icon = "🧩", label = "Activities", isSelected = false)
                        SubTabItem(icon = "👤", label = "Profile", isSelected = true)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Box(
                            modifier = Modifier
                                .width(70.dp)
                                .height(3.dp)
                                .background(AboutTextDark, RoundedCornerShape(1.5.dp))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Logo Box
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                AboutMiniLogo(modifier = Modifier.size(36.dp))
            }

            Spacer(modifier = Modifier.height(14.dp))

            // App Title & Tagline
            Text(
                text = state.appName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AboutTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = state.tagline,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AboutTealPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = state.versionText,
                fontSize = 12.sp,
                color = AboutTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description Paragraph
            Text(
                text = state.descriptionText,
                fontSize = 14.sp,
                color = AboutTextDark,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Disclaimer Box Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Text(
                    text = state.disclaimerText,
                    fontSize = 12.sp,
                    color = AboutTextGray,
                    modifier = Modifier.padding(16.dp),
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Links List Cards
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.links.forEach { link ->
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
                                        .background(AboutCardBlueBg, RoundedCornerShape(10.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = link.iconEmoji, fontSize = 16.sp)
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = link.title,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AboutTextDark
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = link.subtitle,
                                        fontSize = 12.sp,
                                        color = AboutTextGray
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = { viewModel.openLink(link.id) },
                                modifier = Modifier.height(36.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = AboutCardBlueBg),
                                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
                            ) {
                                Text(text = "Open", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AboutTextDark)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer Quote
            Text(
                text = state.footerQuote,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = AboutTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SubTabItem(icon: String, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) AboutMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) AboutTealPrimary else AboutTextGray
        )
    }
}

@Composable
fun AboutMiniLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val leafPath = Path().apply {
            moveTo(w * 0.50f, h * 0.12f)
            cubicTo(w * 0.88f, h * 0.12f, w * 0.95f, h * 0.58f, w * 0.50f, h * 0.90f)
            cubicTo(w * 0.05f, h * 0.58f, w * 0.12f, h * 0.12f, w * 0.50f, h * 0.12f)
            close()
        }
        drawPath(path = leafPath, color = AboutTealPrimary)
    }
}

@Composable
fun AboutBottomBar(selectedTab: Int) {
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
            AboutBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            AboutBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            AboutBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            AboutBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun AboutBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) AboutMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) AboutTealPrimary else AboutTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    MindbridgeTheme {
        AboutScreen()
    }
}
