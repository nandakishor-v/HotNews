package com.example.blooddonationapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable(route = "login") {
            //call LoginScreen composable function here
        }

        composable(route = "home") {
            //call HomeScreen composable function here
        }

    }
}
