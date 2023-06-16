package com.example.hotnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hotnews.presentation.news_screen.NewsScreen
import com.example.hotnews.presentation.theme.HotNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotNewsTheme {
                NewsScreen()

            }

        }
    }
}

