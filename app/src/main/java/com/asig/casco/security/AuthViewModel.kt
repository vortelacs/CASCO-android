package com.asig.casco.security

import android.content.Context
import android.util.Log
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asig.casco.api.utils.NetworkConnectionInterceptor
import com.asig.casco.model.Token
import kotlinx.coroutines.flow.first
import java.net.SocketTimeoutException

class AuthViewModel(
    context : Context
) : ViewModel() {

    private val authService = AuthService()
    private val tokenStorage =  TokenStorage(context)
    private val networkConnectionInterceptor = NetworkConnectionInterceptor(context)


    fun login(username: String, password: String) : Boolean {

        networkConnectionInterceptor.isNetworkAvailable()
        var result = false
        var token: Token? = null

        viewModelScope.launch {
            try {
            token = authService.sendLoginRequest(username, password)
            }catch (e: Exception) {
                print(e)
                result = false
            }

            if (token != null) {
                Log.i("token", token!!.token)
                tokenStorage.saveData(token!!.token)
                if (checkTokenValid()) result = false
            }
        }

        return result
    }

    fun checkTokenValid(): Boolean {
        var result = false
        var token = Token("")

        viewModelScope.launch {
            val string = tokenStorage.getToken.first()
            if (string != null) {
                Log.i("token123", string)
            }
            if (string != null && string != "")
                token = string.let { Token(it) }!!
            if (tokenStorage.isKeyStored(TokenStorage.TOKEN).first()) {
                result = try {
                    authService.sendCheckTokenRequest(token) == true
                }catch (e: SocketTimeoutException) {
                    print(e)
                    false
                }
        }

        }

        return result
    }

}