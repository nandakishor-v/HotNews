package com.example.hotnews

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hotnews.ui.theme.HotNewsTheme


import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.firebase.auth.FirebaseAuth

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App();
                }
            }
        }
    }
}

@Composable
fun App(){
    val context = LocalContext.current

        val auth = FirebaseAuth.getInstance()
        val authStateListener = { auth: FirebaseAuth ->
            val user = auth.currentUser
            if (user != null) {
                // User is logged in, navigate to the home screen or other authenticated screens
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            } else {
                // User is not logged in, navigate to the login screen or other unauthenticated screens
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
        }

        auth.addAuthStateListener(authStateListener)

        DisposableEffect(Unit) {
            onDispose {
                auth.removeAuthStateListener(authStateListener)
            }

    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription ="Logo" )
    }
}