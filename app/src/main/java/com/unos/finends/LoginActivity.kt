package com.unos.finends

//import NavigationGraph
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.unos.finends.core.navigation.NavigationItems
import com.unos.finends.features.history.HistoryScreen
import com.unos.finends.features.profile.ProfileScreen
import com.unos.finends.screen.SplashScreen
import com.unos.finends.features.statistic.StatisticScreen
import com.unos.finends.ui.theme.FinendsTheme
import com.unos.finends.features.home.HomeScreen
import com.unos.finends.features.auth.Login
import com.unos.finends.features.auth.SignUpScreen
import kotlinx.coroutines.launch

const val WEB_CLIENT_ID = "208330382096-dirh84ott4cst572d4sld2trcqe9f8ps.apps.googleusercontent.com"
enum class Screen {
    Login, Home, SignUp,
}

lateinit var auth: FirebaseAuth

@Composable
fun LoginActivity() {
    auth = FirebaseAuth.getInstance()
    FinendsTheme {
        val navController = rememberNavController()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val credentialManager = CredentialManager.create(context)

        NavHost(navController = navController, startDestination = "SplashScreen") {
            composable("SplashScreen") {
                SplashScreen()
                LaunchedEffect(key1 = true) {
                    kotlinx.coroutines.delay(1500)
                    val startDestination = if (auth.currentUser == null) Screen.Login.name else Screen.Home.name
                    navController.popBackStack()
                    navController.navigate(startDestination)
                }
            }
            composable(Screen.Login.name) {
                Login(
                    onSignInClick = {
                        val googleIdOption = GetGoogleIdOption.Builder()
                            .setFilterByAuthorizedAccounts(false)
                            .setServerClientId(WEB_CLIENT_ID)
                            .build()

                        val request = GetCredentialRequest.Builder()
                            .addCredentialOption(googleIdOption)
                            .build()

                        scope.launch {
                            try {
                                val result = credentialManager.getCredential(context = context, request = request)
                                val credential = result.credential
                                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                                val googleIdToken = googleIdTokenCredential.idToken
                                val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

                                auth.signInWithCredential(firebaseCredential)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            navController.navigate(Screen.Home.name) {
                                                popUpTo(Screen.Login.name) { inclusive = true }
                                            }
                                        } else {
                                            Toast.makeText(context, "Sign-in failed.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    onNavigateToSignUp = {
                        navController.navigate(Screen.SignUp.name)
                    }
                )
            }
            composable(Screen.SignUp.name) {
                SignUpScreen(navController = navController,
                    onSignInClick = {
                        val googleIdOption = GetGoogleIdOption.Builder()
                            .setFilterByAuthorizedAccounts(false)
                            .setServerClientId(WEB_CLIENT_ID)
                            .build()

                        val request = GetCredentialRequest.Builder()
                            .addCredentialOption(googleIdOption)
                            .build()

                        scope.launch {
                            try {
                                val result = credentialManager.getCredential(context = context, request = request)
                                val credential = result.credential
                                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                                val googleIdToken = googleIdTokenCredential.idToken
                                val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

                                auth.signInWithCredential(firebaseCredential)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            navController.navigate(Screen.Home.name) {
                                                popUpTo(Screen.Login.name) { inclusive = true }
                                            }
                                        } else {
                                            Toast.makeText(context, "Sign-in failed.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
            }
            composable(NavigationItems.Home.route) {
//                NavigationGraph(navController = navController)
                HomeScreen(currentUser = auth.currentUser,
                    navController = navController,
                    onSignOutClick = {
                        auth.signOut()
                        scope.launch {
                            credentialManager.clearCredentialState(ClearCredentialStateRequest())
                        }
                        navController.navigate(Screen.Login.name) {
                            popUpTo(Screen.Login.name) { inclusive = true }
                        }
                    }
                )
            }
            composable(NavigationItems.History.route) {
               HistoryScreen(navController = navController)
            }
            composable(NavigationItems.Statistic.route) {
                StatisticScreen(navController = navController)
            }
            composable(NavigationItems.Profile.route) {
                ProfileScreen(navController = navController, currentUser = auth.currentUser )
            }

        }
    }
}

