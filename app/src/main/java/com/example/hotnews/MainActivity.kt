package com.example.hotnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.hotnews.presentation.news_screen.NewsScreen
import com.example.hotnews.presentation.news_screen.NewsScreenViewModel
import com.example.hotnews.presentation.theme.HotNewsTheme
import com.example.hotnews.util.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotNewsTheme {
                val navController = rememberNavController()
                NavGraphSetup(navController = navController)
                val viewModel: NewsScreenViewModel = hiltViewModel()

            }

        }
    }
}

