package com.unos.finends.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.unos.finends.R
import com.unos.finends.data.model.SignInViewModel
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.unos.finends.Screen
import com.unos.finends.data.model.LoginViewModel
import com.unos.finends.ui.theme.YelGreen


@Composable
fun SignUpScreen(
    signInViewModel : SignInViewModel = viewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit
)
{
    val signInData = signInViewModel.signInData
    val isLoading = signInViewModel.isLoading
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isNameFocused by remember { mutableStateOf(false) }
    var isEmailFocused by remember { mutableStateOf(false) }
    var isPasswordFocused by remember { mutableStateOf(false) }
    var isConfirmPasswordFocused by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Finends",
                    modifier = Modifier.size(25.dp)
                )
            }
            Text(
                text = "Sign Up",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Column(modifier = modifier) {
                // Username field
                OutlinedTextField(
                    value = signInData.username,
                    onValueChange = { signInViewModel.onUsernameChanged(it) },
                    label = {
                        if (!isNameFocused && signInData.username.isEmpty()) {
                            Text(text = "Name", fontSize = 14.sp, color = Color.LightGray)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isNameFocused = it.isFocused }
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(2f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Email field
                OutlinedTextField(
                    value = signInData.email,
                    onValueChange = { signInViewModel.onEmailChanged(it) },
                    label = {
                        if (!isEmailFocused && signInData.email.isEmpty()) {
                            Text(text = "Email", fontSize = 14.sp, color = Color.LightGray)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isEmailFocused = it.isFocused }
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(2f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Password field
                OutlinedTextField(
                    value = signInData.password,
                    onValueChange = { signInViewModel.onPasswordChanged(it) },
                    label = {
                        if (!isPasswordFocused && signInData.password.isEmpty()) {
                            Text(text = "Password", fontSize = 14.sp, color = Color.LightGray)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isPasswordFocused = it.isFocused }
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(if (passwordVisible) R.drawable.eye else R.drawable.password),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Confirm password field
                OutlinedTextField(
                    value = signInData.confirmPassword,
                    onValueChange = { signInViewModel.onConfirmChanged(it) },
                    label = {
                        if (!isConfirmPasswordFocused && signInData.confirmPassword.isEmpty()) {
                            Text(text = "Confirm password", fontSize = 14.sp, color = Color.LightGray)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isConfirmPasswordFocused = it.isFocused }
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                painter = painterResource(if (confirmPasswordVisible) R.drawable.eye else R.drawable.password),
                                contentDescription = if (confirmPasswordVisible) "Hide confirm password" else "Show confirm password",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                )
            }

            Button(
                onClick = {
                    signInViewModel.signIn()
                    navController.navigate(Screen.Home.name)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = YelGreen,
                )
            ) {
                Text(text = if (isLoading) "Loading..." else "Sign Up")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 0.5.dp,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "or login with", fontSize = 14.sp, color = Color.LightGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    HorizontalDivider(
                        modifier = Modifier.weight(1f), thickness = 0.5.dp,
                        color = Color.LightGray
                    )}
                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    SocialMedia(icon = R.drawable.google) {onSignInClick()}
                    SocialMedia(icon = R.drawable.facebook) { onSignInClick()}
                }
            }

            // Already have an account
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.2f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row {
                    Text(
                        text = "Already have an account? ",
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Text(
                        text = "Login",
                        style = TextStyle(
                            color = YelGreen,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                                navController.navigate(Screen.Login.name)
                            }
                    )}}
        }
    }}

