package com.asig.casco.security

import com.asig.casco.model.Token
import com.asig.casco.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi{

    @POST("auth/login")
    suspend fun login(@Body credentials : User): Token

    @POST("auth/validateToken")
    suspend fun checkToken(@Body token : String = ""): Boolean
}