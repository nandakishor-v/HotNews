package com.example.hotnews.presentation.news_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.hotnews.domain.model.Article

@Composable
fun NewsScreen(
    viewModel: NewsScreenViewModel
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp) ){
        items(viewModel.articles){
           article ->
            Text(text = article.title)

        }
fghfh
    }
}