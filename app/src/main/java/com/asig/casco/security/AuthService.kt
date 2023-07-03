package com.asig.casco.security

import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.model.SignUpRequest
import com.asig.casco.model.Token
import com.asig.casco.model.User

class AuthService(
) {
    private val retrofitFactory = RetrofitFactory()

    suspend fun sendLoginRequest(username : String, password : String) : Token{

        val retrofit = retrofitFactory.getRetrofitInstance()

        val userService = retrofit.create(AuthApi::class.java)
        val token =  userService.login(User(username, password)).token
        return Token(token)
    }

    suspend fun sendCheckTokenRequest(token: Token): Boolean {

        val retrofit = retrofitFactory.getRetrofitInstance()

        val userService = retrofit.create(AuthApi::class.java)

        return userService.checkToken(token.token)

    }



    suspend fun sendSignUpRequest(request : SignUpRequest) : Token{

        val retrofit = retrofitFactory.getRetrofitInstance()

        val userService = retrofit.create(AuthApi::class.java)
        val token =  userService.signUp(request).token
        return Token(token)
    }
}