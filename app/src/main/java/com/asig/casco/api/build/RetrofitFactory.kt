package com.asig.casco.api.build

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.23.158.39:8080/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitInstance(): Retrofit {
        return retrofit
    }
}