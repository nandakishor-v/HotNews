package com.example.hotnews.domain.repository

import com.example.hotnews.domain.model.Article
import com.example.hotnews.util.Resource

interface NewsRepository {
    suspend fun getTopHeadlines(
        category: String
    ):Resource<List<Article>>

    suspend fun searchForNews(
        query: String
    ):Resource<List<Article>>
}