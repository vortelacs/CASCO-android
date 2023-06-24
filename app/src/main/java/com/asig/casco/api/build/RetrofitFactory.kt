package com.asig.casco.api.build

import android.content.Context
import com.asig.casco.api.utils.NetworkConnectionInterceptor
import com.asig.casco.security.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory (){

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.31.192:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitInstance(): Retrofit {
        return retrofit
    }



}