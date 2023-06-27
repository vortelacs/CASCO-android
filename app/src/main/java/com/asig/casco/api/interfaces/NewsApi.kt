package com.asig.casco.api.interfaces

import com.asig.casco.model.Insurance
import com.asig.casco.model.News
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

public interface NewsApi {

    @GET("news")
    suspend fun getAllNews(@Header("Authorization") accessToken: String): ArrayList<News>
}