package com.example.hotnews.presentation.news_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotnews.domain.model.Article
import com.example.hotnews.domain.repository.NewsRepository
import com.example.hotnews.util.Resource
import kotlinx.coroutines.launch

class NewsScreenViewModel(
    private val newsRepository: NewsRepository
): ViewModel{
    val articles by mutableStateOf<List<Article>>(emptyList<>())
    private fun getNewsArticles(category: String){
       viewModelScope.launch {
           val result = newsRepository.getTopHeadlines(category = category)
           when(result){
               is Resource.Error -> TODO()
               is Resource.Success -> {
                   articles = result.data ?: emptyList()
               }

           }
       }
    }
}