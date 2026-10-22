package com.example.mindbridge

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindbridge.ui.theme.MindbridgeTheme

// Color definitions matching design
val SplashBackground = Color(0xFFE4EFF5)
val SplashTealCircle = Color(0xFFCBE5DC)
val SplashYellowCircle = Color(0xFFFFF5E4)
val SplashLogoTeal = Color(0xFF4CA695)
val SplashTitleColor = Color(0xFF1E2A38)
val SplashSubtitleColor = Color(0xFF5B6E80)

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SplashBackground)
    ) {
        // Decorative Background Shapes
        
        // 1. Top-Left Soft Teal Circle
        Box(
            modifier = Modifier
                .offset(x = (-100).dp, y = (-120).dp)
                .size(340.dp)
                .clip(CircleShape)
                .background(SplashTealCircle)
        )

        // 2. Top-Right Soft Yellow/Cream Circle
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-50).dp, y = 140.dp)
                .size(52.dp)
                .clip(CircleShape)
                .background(SplashYellowCircle)
        )

        // 3. Bottom-Right Soft Teal Circle
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 100.dp, y = 120.dp)
                .size(320.dp)
                .clip(CircleShape)
                .background(SplashTealCircle)
        )

        // Center Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // White rounded box containing the logo
            Box(
                modifier = Modifier
                    .size(108.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(28.dp),
                        spotColor = Color(0x10000000),
                        ambientColor = Color(0x10000000)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(28.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                MindBridgeLogo(modifier = Modifier.size(58.dp))
            }

            Spacer(modifier = Modifier.height(28.dp))

            // App Title
            Text(
                text = "MindBridge AI",
                color = SplashTitleColor,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // App Subtitle
            Text(
                text = "Helping memories stay connected.",
                color = SplashSubtitleColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        }

        // Bottom Content (Heart Icon + Tagline)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HeartOutlineIcon(
                modifier = Modifier.size(18.dp),
                color = SplashLogoTeal
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = "A little support. Every day.",
                color = SplashSubtitleColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

/**
 * Custom Canvas composable for the MindBridge leaf logo shape and inner "Y" vein.
 */
@Composable
fun MindBridgeLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Leaf background path
        val leafPath = Path().apply {
            moveTo(w * 0.50f, h * 0.12f)
            // Right curve down
            cubicTo(
                w * 0.88f, h * 0.12f,
                w * 0.95f, h * 0.58f,
                w * 0.50f, h * 0.90f
            )
            // Left curve back up
            cubicTo(
                w * 0.05f, h * 0.58f,
                w * 0.12f, h * 0.12f,
                w * 0.50f, h * 0.12f
            )
            close()
        }

        drawPath(
            path = leafPath,
            color = SplashLogoTeal
        )

        // White "Y" vein structure
        val strokeWidthPx = 3.5.dp.toPx()
        val whiteVeinColor = Color.White

        // Main vertical stem
        drawLine(
            color = whiteVeinColor,
            start = Offset(w * 0.50f, h * 0.72f),
            end = Offset(w * 0.50f, h * 0.44f),
            strokeWidth = strokeWidthPx,
            cap = StrokeCap.Round
        )

        // Left branch
        val leftBranchPath = Path().apply {
            moveTo(w * 0.50f, h * 0.46f)
            cubicTo(
                w * 0.42f, h * 0.38f,
                w * 0.34f, h * 0.32f,
                w * 0.28f, h * 0.28f
            )
        }
        drawPath(
            path = leftBranchPath,
            color = whiteVeinColor,
            style = Stroke(
                width = strokeWidthPx,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // Right branch
        val rightBranchPath = Path().apply {
            moveTo(w * 0.50f, h * 0.46f)
            cubicTo(
                w * 0.58f, h * 0.38f,
                w * 0.66f, h * 0.32f,
                w * 0.72f, h * 0.28f
            )
        }
        drawPath(
            path = rightBranchPath,
            color = whiteVeinColor,
            style = Stroke(
                width = strokeWidthPx,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}

/**
 * Custom Canvas composable for the outline heart icon at the bottom.
 */
@Composable
fun HeartOutlineIcon(
    modifier: Modifier = Modifier,
    color: Color = SplashLogoTeal
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val heartPath = Path().apply {
            moveTo(w * 0.50f, h * 0.82f)
            cubicTo(
                w * 0.15f, h * 0.55f,
                w * 0.02f, h * 0.28f,
                w * 0.26f, h * 0.14f
            )
            cubicTo(
                w * 0.40f, h * 0.05f,
                w * 0.50f, h * 0.22f,
                w * 0.50f, h * 0.28f
            )
            cubicTo(
                w * 0.50f, h * 0.22f,
                w * 0.60f, h * 0.05f,
                w * 0.74f, h * 0.14f
            )
            cubicTo(
                w * 0.98f, h * 0.28f,
                w * 0.85f, h * 0.55f,
                w * 0.50f, h * 0.82f
            )
            close()
        }

        drawPath(
            path = heartPath,
            color = color,
            style = Stroke(
                width = 1.8.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    MindbridgeTheme {
        SplashScreen()
    }
}
