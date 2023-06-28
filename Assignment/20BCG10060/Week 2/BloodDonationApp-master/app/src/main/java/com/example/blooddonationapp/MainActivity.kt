package com.example.blooddonationapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


sealed class Destination(val route:String){
    object Main: Destination("Main")
    object Login: Destination("Login")
    object Signup: Destination("Signup")
    object Home: Destination("Home")
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationAppHost(navController = navController)

        }
    }
}
@Composable
fun NavigationAppHost(navController: NavHostController){
    NavHost(navController =navController , startDestination = "Home"){
        composable(Destination.Main.route){
            Mainscreen()
        }
        composable(Destination.Login.route) {
            LoginScreen(navController)
        }
        composable(Destination.Signup.route) {
            SignUpScreen(navController)
        }
        composable(Destination.Home.route) {
            HomeScreen(navController)
        }
    }

}