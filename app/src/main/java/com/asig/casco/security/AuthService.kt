package com.asig.casco.security

import android.content.Context
import android.util.Log
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.model.Token
import com.asig.casco.model.User

class AuthService(
) {
    private val retrofitFactory = RetrofitFactory()

    suspend fun sendLoginRequest(username : String, password : String) : Token?{

        val retrofit = retrofitFactory.getRetrofitInstance()

        val userService = retrofit.create(AuthApi::class.java)
        Log.i("credentials","$username  $password")
        val token =  userService.login(User(username, password)).token
        Log.i("token12", token)
        return Token(token)
    }

    suspend fun sendCheckTokenRequest(token: Token): Boolean? {

        val retrofit = retrofitFactory.getRetrofitInstance()

        val userService = retrofit.create(AuthApi::class.java)

        return userService.checkToken(token.token)

    }
}