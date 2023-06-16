package com.example.hotnews.presentation.news_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hotnews.domain.model.Article
import com.example.hotnews.presentation.components.NewsArticleCard
import com.example.hotnews.presentation.components.NewsScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsScreenViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            NewsScreenTopBar(onSearchIconClicked = {})
        }
    ) { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(padding)
        ){
            items(viewModel.articles){
               article ->
                NewsArticleCard(
                    article = article,
                    onCardClicked = {}
                )
                }
            }
    }
}