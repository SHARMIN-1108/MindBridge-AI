package com.example.mindbridge.ui.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme

val RegisterBackground = Color(0xFFFAF6EE)
val RegisterTealPrimary = Color(0xFF2E695F)
val RegisterTextDark = Color(0xFF1E2A38)
val RegisterTextGray = Color(0xFF5B6E80)
val RegisterBorderColor = Color(0xFFD8E2DC)

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isRegisterSuccess) {
        onRegisterSuccess()
    }
    if (state.navigateToLogin) {
        onLoginClick()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RegisterBackground)
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Top Bar: Back Arrow & "Join MindBridge AI" title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                    Text(text = "←", fontSize = 20.sp, color = RegisterTextDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Join MindBridge AI",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RegisterTextDark
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Heading & Subtitle
            Text(
                text = "Create your account",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = RegisterTextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "A little everyday support starts here.",
                fontSize = 14.sp,
                color = RegisterTextGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Full Name Field
            Text(text = "Full Name", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = RegisterTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.fullName,
                onValueChange = { viewModel.onFullNameChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RegisterTealPrimary,
                    unfocusedBorderColor = RegisterBorderColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true,
                trailingIcon = {
                    Text(text = "✓", color = RegisterTealPrimary, fontSize = 16.sp, modifier = Modifier.padding(end = 12.dp))
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Email Field
            Text(text = "Email", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = RegisterTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RegisterTealPrimary,
                    unfocusedBorderColor = RegisterBorderColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                trailingIcon = {
                    Text(text = "✓", color = RegisterTealPrimary, fontSize = 16.sp, modifier = Modifier.padding(end = 12.dp))
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Password Field
            Text(text = "Password", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = RegisterTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RegisterTealPrimary,
                    unfocusedBorderColor = RegisterBorderColor,
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
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Use at least 8 characters. You're all set.",
                fontSize = 12.sp,
                color = RegisterTextGray
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Confirm Password Field
            Text(text = "Confirm Password", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = RegisterTextDark)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = state.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RegisterTealPrimary,
                    unfocusedBorderColor = RegisterBorderColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true,
                visualTransformation = if (state.confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 8.dp)) {
                        TextButton(onClick = { viewModel.toggleConfirmPasswordVisibility() }) {
                            Text(text = if (state.confirmPasswordVisible) "👁" else "👁‍🗨", fontSize = 16.sp)
                        }
                        Text(text = "✓", color = RegisterTealPrimary, fontSize = 16.sp)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Role selection: "I'm joining as a..."
            Text(text = "I'm joining as a...", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = RegisterTextDark)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Option 1: Person using the app
                val isPerson = state.role == UserRole.PERSON_USING_APP
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isPerson) Color(0xFFE4EFF5) else Color.White)
                        .border(
                            BorderStroke(
                                width = if (isPerson) 2.dp else 1.dp,
                                color = if (isPerson) RegisterTealPrimary else RegisterBorderColor
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { viewModel.onRoleSelected(UserRole.PERSON_USING_APP) }
                        .padding(12.dp)
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "👤", fontSize = 16.sp)
                            if (isPerson) {
                                Text(text = "✓", color = RegisterTealPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Person using the app",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = RegisterTextDark,
                            lineHeight = 16.sp
                        )
                    }
                }

                // Option 2: Family caregiver
                val isCaregiver = state.role == UserRole.FAMILY_CAREGIVER
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isCaregiver) Color(0xFFE4EFF5) else Color.White)
                        .border(
                            BorderStroke(
                                width = if (isCaregiver) 2.dp else 1.dp,
                                color = if (isCaregiver) RegisterTealPrimary else RegisterBorderColor
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { viewModel.onRoleSelected(UserRole.FAMILY_CAREGIVER) }
                        .padding(12.dp)
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "♡", fontSize = 16.sp, color = RegisterTealPrimary)
                            if (isCaregiver) {
                                Text(text = "✓", color = RegisterTealPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Family caregiver",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = RegisterTextDark,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Terms agreement checkbox row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.agreedToTerms,
                    onCheckedChange = { viewModel.onTermsToggled(it) },
                    colors = CheckboxDefaults.colors(checkedColor = RegisterTealPrimary)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "I agree to the privacy policy and how my information is used. I can change my choices later.",
                    fontSize = 11.sp,
                    color = RegisterTextGray,
                    lineHeight = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Create Account Button
            Button(
                onClick = { viewModel.register() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RegisterTealPrimary),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text(text = "Create Account", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Bottom link: "Already have an account? Log in"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Already have an account? ", fontSize = 13.sp, color = RegisterTextGray)
                Text(
                    text = "Log in",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = RegisterTealPrimary,
                    modifier = Modifier.clickable { viewModel.navigateToLogin() }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    MindbridgeTheme {
        RegisterScreen()
    }
}
