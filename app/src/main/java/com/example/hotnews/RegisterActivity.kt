package com.example.hotnews

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotnews.ui.theme.HotNewsTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    RegisterScreen()
                }
            }
        }
    }
}

@Composable
fun RegisterScreen() {
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
                text = "Register",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
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
                    registerWithEmailAndPassword(email, password, context)
                },
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login with Google option

//            Row(
//            ) {
//                Button(
//                    onClick = { /* Button click handler */ },
//                    modifier = Modifier
//                        .background(Color.White, shape = RoundedCornerShape(8.dp))
//                        .fillMaxWidth()
//                        .height(50.dp)
//                    ,colors = ButtonDefaults.buttonColors(Color(0xFFE4E4E4))
//
//
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.google,),
//                        contentDescription = "Trailing Icon",
//                        modifier = Modifier
//                            .size(40.dp)
//                            .padding(bottom = 4.dp),
//                    )
//                    Spacer(modifier = Modifier.width(32.dp))
//                    Text(
//                        text = "Sign Up with Google",
//                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
//                        ),
//                        modifier = Modifier.padding(end = 8.dp)
//
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(24.dp))



            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Already a User? ",
                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                )

                Text(
                    text = "Sign In",
                    modifier = Modifier.clickable {
                        val intent = Intent(context, LoginActivity::class.java)

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


private fun registerWithEmailAndPassword(email: String, password: String, context: Context) {
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast(context=context,"Registration Success!")
                val intent = Intent(context, LoginActivity::class.java)

                context.startActivity(intent)
                // Registration successful, handle the newly registered user
                // You can also automatically sign in the user after registration if desired
            } else {
                showToast(context=context,"Registration Failed!")
                // Registration failed, handle the error
            }
        }
}