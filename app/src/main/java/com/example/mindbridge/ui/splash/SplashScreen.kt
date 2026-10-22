package com.example.mindbridge.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindbridge.R
import com.example.mindbridge.ui.theme.MindbridgeTheme

/**
 * Splash Screen composable displaying the saved image vector asset from drawable
 * along with precise typography matching the design specs.
 */
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background illustration from drawable/splash.xml
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Splash Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Content overlay positioning text elements relative to the logo card
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(310.dp))

            // App Name Title
            Text(
                text = "MindBridge AI",
                color = Color(0xFF1E2A38),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "Helping memories stay connected.",
                color = androidx.compose.ui.graphics.Color(0xFF5B6E80),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            // Footer Support Text
            Text(
                text = "♡ A little support. Every day.",
                color = androidx.compose.ui.graphics.Color(0xFF5B6E80),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    MindbridgeTheme {
        SplashScreen()
    }
}
