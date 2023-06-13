package com.example.hotnews.presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotnews.domain.model.Article
import com.example.hotnews.domain.repository.NewsRepository
import com.example.hotnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    var articles by mutableStateOf<List<Article>>(emptyList())
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