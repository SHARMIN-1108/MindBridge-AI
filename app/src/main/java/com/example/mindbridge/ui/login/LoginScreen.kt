package com.example.mindbridge.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val LoginBackground = Color(0xFFFAF6EE)
val LoginTealPrimary = Color(0xFF2E695F)
val LoginTextDark = Color(0xFF1E2A38)
val LoginTextGray = Color(0xFF5B6E80)
val LoginBorderColor = Color(0xFFD8E2DC)
val LoginInfoBoxBg = Color(0xFFE4EFF5)

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isLoginSuccess) {
        onLoginSuccess()
    }
    if (state.navigateToRegister) {
        onCreateAccountClick()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LoginBackground)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.Start
        ) {
            // Top Bar: Back Arrow & "Log in" title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = LoginTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Log in",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = LoginTextDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Logo & Brand
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .shadow(1.dp, RoundedCornerShape(8.dp))
                        .background(Color.White, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    LoginMiniLogo(modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "MindBridge AI",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = LoginTextDark
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Heading & Subtitle
            Text(
                text = "Welcome back",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = LoginTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Let's reconnect with your day.",
                fontSize = 14.sp,
                color = LoginTextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email Field Label
            Text(
                text = "Email",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = LoginTextDark
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = LoginTealPrimary,
                    unfocusedBorderColor = LoginBorderColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field Label
            Text(
                text = "Password",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = LoginTextDark
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = LoginTealPrimary,
                    unfocusedBorderColor = LoginBorderColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true,
                visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    TextButton(onClick = { viewModel.togglePasswordVisibility() }) {
                        Text(text = if (state.passwordVisible) "👁" else "👁‍🗨", fontSize = 16.sp)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot password?
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = "Forgot password?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LoginTealPrimary,
                    modifier = Modifier.clickable { /* Handle forgot password */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Info Card Box
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(LoginInfoBoxBg)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "ⓘ", fontSize = 18.sp, color = LoginTealPrimary)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Enter your email and password. We'll help if you need it.",
                    fontSize = 12.sp,
                    color = LoginTextGray,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Log In Button
            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LoginTealPrimary),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text(text = "Log In", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Section: New to MindBridge AI? Create account
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "New to MindBridge AI?",
                    fontSize = 13.sp,
                    color = LoginTextGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { viewModel.navigateToRegister() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, LoginBorderColor),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "Create account",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LoginTextDark
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Footer Privacy Notice
                Text(
                    text = "Your memories are personal. We keep them private.",
                    fontSize = 11.sp,
                    color = LoginTextGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun LoginMiniLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val leafPath = Path().apply {
            moveTo(w * 0.50f, h * 0.12f)
            cubicTo(w * 0.88f, h * 0.12f, w * 0.95f, h * 0.58f, w * 0.50f, h * 0.90f)
            cubicTo(w * 0.05f, h * 0.58f, w * 0.12f, h * 0.12f, w * 0.50f, h * 0.12f)
            close()
        }
        drawPath(path = leafPath, color = LoginTealPrimary)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MindbridgeTheme {
        LoginScreen()
    }
}
