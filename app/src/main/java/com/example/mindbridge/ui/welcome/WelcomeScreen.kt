package com.example.mindbridge.ui.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mindbridge.R
import com.example.mindbridge.ui.theme.MindbridgeTheme

// Color definitions matching the Welcome Screen design
val WelcomeBackground = Color(0xFFFAF6EE) // Warm cream background
val WelcomeTealPrimary = Color(0xFF2E695F) // Deep teal for Get Started button & accents
val WelcomeTextDark = Color(0xFF1E2A38)
val WelcomeTextGray = Color(0xFF5B6E80)
val WelcomeBorderColor = Color(0xFFD8E2DC)

@Composable
fun WelcomeScreen(
    onGetStartedClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(WelcomeBackground)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Header: Logo + MindBridge AI
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Small logo box
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .shadow(2.dp, RoundedCornerShape(10.dp))
                        .background(Color.White, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    MindBridgeMiniLogo(modifier = Modifier.size(20.dp))
                }

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "MindBridge AI",
                    color = WelcomeTextDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Center Illustration Card displaying the welcome image from drawable
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(24.dp),
                        spotColor = Color(0x15000000)
                    )
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome),
                    contentDescription = "Welcome Family Illustration",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Heading & Subtitle
            Text(
                text = "Your memories matter.",
                color = WelcomeTextDark,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = (-0.5).sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "A supportive companion for everyday routines, memories, and meaningful moments.",
                color = WelcomeTextGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp),
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Action Buttons & Footer
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Get Started Button
                Button(
                    onClick = onGetStartedClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = WelcomeTealPrimary),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "→ Get Started",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // I already have an account Button
                OutlinedButton(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, WelcomeBorderColor),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "I already have an account",
                        color = WelcomeTextDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Footer Privacy Notice
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    PrivacyShieldIcon(
                        modifier = Modifier.size(14.dp),
                        color = WelcomeTextGray
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = "Your privacy and comfort matter.",
                        color = WelcomeTextGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

/**
 * Custom mini leaf logo composable for header.
 */
@Composable
fun MindBridgeMiniLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val leafPath = Path().apply {
            moveTo(w * 0.50f, h * 0.12f)
            cubicTo(w * 0.88f, h * 0.12f, w * 0.95f, h * 0.58f, w * 0.50f, h * 0.90f)
            cubicTo(w * 0.05f, h * 0.58f, w * 0.12f, h * 0.12f, w * 0.50f, h * 0.12f)
            close()
        }
        drawPath(path = leafPath, color = WelcomeTealPrimary)
    }
}



/**
 * Custom shield/lock icon for privacy note.
 */
@Composable
fun PrivacyShieldIcon(modifier: Modifier = Modifier, color: Color = WelcomeTextGray) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val path = Path().apply {
            moveTo(w * 0.5f, h * 0.1f)
            lineTo(w * 0.85f, h * 0.25f)
            lineTo(w * 0.85f, h * 0.55f)
            cubicTo(w * 0.85f, h * 0.8f, w * 0.5f, h * 0.95f, w * 0.5f, h * 0.95f)
            cubicTo(w * 0.5f, h * 0.95f, w * 0.15f, h * 0.8f, w * 0.15f, h * 0.55f)
            lineTo(w * 0.15f, h * 0.25f)
            close()
        }
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    MindbridgeTheme {
        WelcomeScreen()
    }
}
