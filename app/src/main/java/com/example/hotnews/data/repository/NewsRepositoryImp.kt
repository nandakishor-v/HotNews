package com.example.hotnews.data.repository


import com.example.hotnews.data.remote.NewsApi
import com.example.hotnews.domain.model.Article
import com.example.hotnews.domain.repository.NewsRepository
import com.example.hotnews.util.Resource

class NewsRepositoryImp (
    private val newsApi: NewsApi
        ):NewsRepository{
    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try{
            val response = newsApi.getBreakingNews(category = category)
            Resource.Success(response.articles)

        }catch (e:Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun searchForNews(query: String): Resource<List<Article>> {
        return try{
            val response = newsApi.searchForNews(query = query)
            Resource.Success(response.articles)

        }catch (e:Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}
