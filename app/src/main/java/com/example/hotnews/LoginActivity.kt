package com.example.hotnews

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotnews.ui.theme.HotNewsTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LoginScreen()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome back",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
//                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Email field
            OutlinedTextField(
                value = emailState,

                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                onValueChange = { emailState = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password field
            OutlinedTextField(
                value = passwordState,
                onValueChange = { passwordState = it},
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Login button
            Button(
                onClick = {
                    val email = emailState
                    val password = passwordState
//                    showToast(context,email+password)
                    signInWithEmailAndPassword(email, password, context)
                },
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Login",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                )
            }


            // Login with Google option

//            Row(
//            ) {
//                Button(
//                    onClick = {
//
//                    },
//                    modifier = Modifier
//                        .background(Color.White, shape = RoundedCornerShape(8.dp))
//                        .fillMaxWidth()
//                        .height(50.dp)
//                    ,colors = ButtonDefaults.buttonColors(Color(0xFFE4E4E4))
//
//
//                ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.google,),
//                            contentDescription = "Trailing Icon",
//                            modifier = Modifier
//                                .size(40.dp)
//                                .padding(bottom = 4.dp),
//                        )
//                        Spacer(modifier = Modifier.width(32.dp))
//                        Text(
//                            text = "Login with Google",
//                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
//                            ),
//                            modifier = Modifier.padding(end = 8.dp)
//
//                        )
//                    }
//            }
            Spacer(modifier = Modifier.height(24.dp))



            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Not a User? ",
                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                )

                Text(
                    text = "Register",
                    modifier = Modifier.clickable {
                        val intent = Intent(context, RegisterActivity::class.java)

                        context.startActivity(intent)
                                                  },
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                )

            }
        }
    }
}


private fun showToast(context: Context, message: String) {
    CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

private fun signInWithEmailAndPassword(email: String, password: String, context: Context) {
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Login successful, handle the logged-in user
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("email",email)
                intent.putExtra("password",password)
                context.startActivity(intent)

            } else {
                // Login failed, handle the error
                val errorMessage = task.exception?.message ?: "Unknown error"
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
}