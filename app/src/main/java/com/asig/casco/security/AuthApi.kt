package com.asig.casco.security

import com.asig.casco.model.SignUpRequest
import com.asig.casco.model.Token
import com.asig.casco.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi{

    @POST("auth/login")
    suspend fun login(@Body credentials : User): Token

    @POST("johnauth/validateToken")
    suspend fun checkToken(@Body token : String = ""): Boolean

    @POST("auth/signup")
    suspend fun signUp(@Body request : SignUpRequest): Token
}