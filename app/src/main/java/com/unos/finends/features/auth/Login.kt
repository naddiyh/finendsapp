package com.unos.finends.features.auth


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unos.finends.R
import com.unos.finends.data.model.LoginViewModel
import com.unos.finends.data.model.SignInViewModel
import com.unos.finends.ui.theme.YelGreen
import kotlin.math.log


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(

    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
){
    val loginData = loginViewModel.loginData
    val isLoading = loginViewModel.isLoading


    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 45.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo Finends" ,
        modifier = Modifier.size(25.dp))
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp,),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(40.dp))
        Text (text = "Log In", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id = R.drawable.login) , contentDescription = "Login Image",
            modifier = Modifier.size(150.dp))
        Spacer(modifier = Modifier.height(50.dp))
//
//        Row(modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start) {
//            Text(text = "Welcome Back,", fontSize = 16.sp, fontWeight = FontWeight.Light)
//        }
//        Spacer(modifier= Modifier.height(10.dp))
        LoginSection(loginViewModel = loginViewModel)

        Button(onClick = { loginViewModel.login() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = YelGreen,
            )
        ){
            Text(text = if (isLoading) "Loading..." else "Login")
        }

        Spacer(modifier= Modifier.height(35.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 0.5.dp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "or login with", fontSize = 14.sp, color = Color.LightGray)
                Spacer(modifier = Modifier.width(8.dp))
                HorizontalDivider(
                    modifier = Modifier.weight(1f), thickness = 0.5.dp,
                    color = Color.Gray
                )}
            Spacer(modifier = Modifier.height(25.dp))

            Row (
                horizontalArrangement = Arrangement.Center
            ){
                SocialMedia(icon = R.drawable.google ) {
                    onSignInClick()
                }
                SocialMedia(icon = R.drawable.facebook) {
                    onSignInClick()
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxHeight(fraction = 0.3f),
            contentAlignment = Alignment.BottomCenter
        ) {

            Row {
                Text(
                    text = "Don't have an account yet? ",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Text(
                    text = "Sign in",
                    style = TextStyle(
                        color = YelGreen,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier .clickable {
                        onNavigateToSignUp()
                    }
                )

            }
        }
    }}

@Composable
private fun LoginSection(loginViewModel: LoginViewModel) {
    OutlinedTextField(
        value = loginViewModel.loginData.email,
        onValueChange = { loginViewModel.onEmailChanged(it) },
        label = { Text(text = "Email", fontSize = 14.sp, color = Color.LightGray) },
        modifier = Modifier
            .fillMaxWidth()
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
    OutlinedTextField(
        value = loginViewModel.loginData.password,
        onValueChange = {loginViewModel.onPasswordChanged(it)},
        label = { Text(text = "Password", fontSize = 14.sp, color = Color.LightGray) },
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val width = size.width
                val height = size.height
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, height),
                    end = Offset(width, height),
                    strokeWidth = strokeWidth
                )
            },
        shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.password),
                contentDescription = "Password Icon",
                modifier = Modifier.size(20.dp)
            )
        }
    )
    Spacer (modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(text = "Forget password?", color = Color.LightGray, fontSize = 12.sp,  style = TextStyle(textDecoration = TextDecoration.Underline) )
    }
    Spacer (modifier = Modifier.height(25.dp))

}




