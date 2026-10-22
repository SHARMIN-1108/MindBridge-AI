package com.example.mindbridge.ui.progress

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

val ProgressBackground = Color(0xFFFAF6EE)
val ProgressTealPrimary = Color(0xFF2E695F)
val ProgressTextDark = Color(0xFF1E2A38)
val ProgressTextGray = Color(0xFF5B6E80)
val ProgressMintBg = Color(0xFFCBE5DC)

@Composable
fun ProgressScreen(
    viewModel: ProgressViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ProgressBackground,
        bottomBar = {
            ProgressBottomBar(selectedTab = state.selectedTab)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Top Bar: Back arrow and "My Activity Progress" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = ProgressTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "My Activity Progress",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProgressTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Header & Date Range
            Text(
                text = "Your week in moments",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = ProgressTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = state.dateRange,
                fontSize = 13.sp,
                color = ProgressTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Top Encouraging Banner Card (mint bg)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ProgressMintBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "♡", fontSize = 22.sp, color = ProgressTealPrimary)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = state.bannerMessage,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ProgressTextDark,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Metric Cards List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                state.metrics.forEach { metric ->
                    MetricCard(metric = metric)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Footer Note
            Text(
                text = state.footerText,
                fontSize = 12.sp,
                color = ProgressTextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MetricCard(metric: MetricItem) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = metric.iconEmoji, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = metric.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ProgressTextDark
                    )
                }
                Text(
                    text = metric.valueText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProgressTealPrimary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = metric.subtitle,
                fontSize = 12.sp,
                color = ProgressTextGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Progress bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFFE4EFF5))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(metric.progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(3.dp))
                        .background(ProgressTealPrimary)
                )
            }
        }
    }
}

@Composable
fun ProgressBottomBar(selectedTab: Int) {
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
            ProgressBottomBarItem(icon = "🏠", label = "Home", isSelected = selectedTab == 0)
            ProgressBottomBarItem(icon = "🖼️", label = "Memories", isSelected = selectedTab == 1)
            ProgressBottomBarItem(icon = "🧩", label = "Activities", isSelected = selectedTab == 2)
            ProgressBottomBarItem(icon = "👤", label = "Profile", isSelected = selectedTab == 3)
        }
    }
}

@Composable
fun ProgressBottomBarItem(
    icon: String,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) ProgressMintBg.copy(alpha = 0.6f) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) ProgressTealPrimary else ProgressTextGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProgressScreenPreview() {
    MindbridgeTheme {
        ProgressScreen()
    }
}
