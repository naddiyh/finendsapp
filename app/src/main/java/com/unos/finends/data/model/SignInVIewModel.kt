package com.unos.finends.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class SignInViewModel : ViewModel() {
    var signInData by mutableStateOf(SignInData())
    var isLoading by mutableStateOf(false)
    var authState by mutableStateOf<AuthState>(AuthState.Unauthenticated)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun onEmailChanged(newEmail: String) {
        signInData = signInData.copy(email = newEmail)
    }

    fun onPasswordChanged(newPassword: String) {
        signInData = signInData.copy(password = newPassword)
    }

    fun onUsernameChanged(newUsername: String) {
        signInData = signInData.copy(username = newUsername)
    }

    fun onConfirmChanged(newConfirm: String) {
        signInData = signInData.copy(confirmPassword = newConfirm)
    }

    fun signIn() {
        isLoading = true
        val email = signInData.email
        val password = signInData.password

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading = false
                if (task.isSuccessful) {
                    val user: FirebaseUser? = task.result?.user
                    user?.let {
                        saveUserData(it.uid, email, signInData.username, password) // Save username after registration
                        updateAuthState(AuthState.Authenticated) // Update authentication state
                    }
                } else {
                    updateAuthState(AuthState.Error(task.exception?.message ?: "Sign-in failed"))
                }
            }
    }

    private fun saveUserData(uid: String, email: String, username: String, password : String) {
        val user = hashMapOf(
            "email" to email,
            "username" to username,
            "password" to password,
        )

        firestore.collection("users")
            .document(uid)
            .set(user)
            .addOnSuccessListener {
                println("User data successfully written!")
            }
            .addOnFailureListener { e ->
                println("Error writing user data: $e")
            }
    }

    private fun updateAuthState(newState: AuthState) {
        authState = newState
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    data class Error(val message: String) : AuthState()
}
