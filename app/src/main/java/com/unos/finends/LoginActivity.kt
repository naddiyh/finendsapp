package com.unos.finends

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.unos.finends.screen.SplashScreen
import com.unos.finends.ui.theme.BottomNavbar
import com.unos.finends.ui.theme.FinendsTheme
import com.unos.finends.ui.theme.HomeScreen
import com.unos.finends.ui.theme.Login
import com.unos.finends.ui.theme.SignUp
import kotlinx.coroutines.launch

const val WEB_CLIENT_ID = "208330382096-dirh84ott4cst572d4sld2trcqe9f8ps.apps.googleusercontent.com"
enum class Screen{
    Login,Home, SignUp
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
        val startDestination = if (auth.currentUser == null) Screen.Login.name else Screen.Home.name

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
                        // Logika sign-in dengan Google
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
                                            navController.popBackStack()
                                            navController.navigate(Screen.Home.name)
                                        }
                                    }

                            } catch (e: Exception) {
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }
                        }
                    },
                    onNavigateToSignUp = {
                        navController.navigate(Screen.SignUp.name)
                    }
                )
            }
            composable(Screen.SignUp.name) {
                SignUp(navController = navController)
            }
            composable(Screen.Home.name) {
                HomeScreen(currentUser = auth.currentUser,
                    onSignOutClick = {
                        auth.signOut()
                        scope.launch {
                            credentialManager.clearCredentialState(ClearCredentialStateRequest())
                        }
                        navController.popBackStack()
                        navController.navigate(Screen.Login.name)
                    }
                )
            }
        }
    }
}
