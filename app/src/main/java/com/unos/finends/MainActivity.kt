package com.unos.finends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.unos.finends.features.auth.Login
import com.unos.finends.features.auth.SignUpScreen
import com.unos.finends.features.home.HomeScreen
import com.unos.finends.features.profile.ProfileScreen
import com.unos.finends.features.statistic.StatisticScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivity()
        }
    }
}

//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            FinendsApp()
//        }
//    }
//}
//
//@Composable
//fun FinendsApp() {
//    val navController = rememberNavController()
//    LoginActivity(navController)
//}
