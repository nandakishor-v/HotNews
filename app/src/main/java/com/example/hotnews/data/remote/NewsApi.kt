package com.example.hotnews.data.remote

import com.example.hotnews.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    //https://newsapi.org/v2/top-headlines?country=in&apiKey=aaa937e593a44c46920b6487b0f8ecd6
    @GET("top-headlines")
    suspend fun getBreakingNews(
    @Query("category") category: String,
    @Query("country") country: String = "in",
    @Query("apiKey") apiKey: String = API_KEY
    ):NewsResponse
    @GET("everything")
    suspend fun searchForNews(
        @Query("Q") query: String ,
        @Query("apiKey") apiKey: String = API_KEY
    ):NewsResponse
    companion object{
        const val BASE_URL="https://newsapi.org/v2/"
        const val API_KEY="aaa937e593a44c46920b6487b0f8ecd6"
    }
}