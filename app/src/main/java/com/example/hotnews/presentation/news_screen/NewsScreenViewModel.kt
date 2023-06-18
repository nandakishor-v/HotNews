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
    var state by mutableStateOf(NewsScreenState())

    fun onEvent(event: NewsScreenEvent){
        when(event){
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticles(state.category)
            }
            NewsScreenEvent.OnCloseIconClicked -> TODO()
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }
            NewsScreenEvent.OnSearchIconClicked -> TODO()
            is NewsScreenEvent.OnSearchQueryChanged -> TODO()
        }
    }

    init{
        getNewsArticles(category = "general")
    }
    private fun getNewsArticles(category: String){
       viewModelScope.launch {
           state = state.copy(isLoading = true)
           val result = newsRepository.getTopHeadlines(category = category)
           when(result){
               is Resource.Error -> {
                  state = state.copy(
                        error = result.message,
                       isLoading = false,
                       articles = emptyList()
                   )
               }
               is Resource.Success -> {
                   state = state.copy(
                       articles = result.data ?: emptyList(),
                       isLoading = false,
                       error = null
                   )
               }

           }
       }
    }
}