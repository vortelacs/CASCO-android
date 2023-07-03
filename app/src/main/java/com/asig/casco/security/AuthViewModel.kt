package com.asig.casco.security

import android.content.Context
import android.util.Log
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asig.casco.api.utils.NetworkConnectionInterceptor
import com.asig.casco.model.SignUpRequest
import com.asig.casco.model.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class AuthViewModel(
    context : Context
) : ViewModel() {

    private val authService = AuthService()
    private val userDataStorage =  UserDataStorage(context)
    private val networkConnectionInterceptor = NetworkConnectionInterceptor(context)

    // Create a LiveData to hold the login result
    private val _loginResult = MutableStateFlow<Boolean>(false)
    val loginResult: StateFlow<Boolean> = _loginResult

    private val _loginAttempted = MutableStateFlow<Boolean>(false)
    val loginAttempted: StateFlow<Boolean> = _loginAttempted


     fun login(username: String, password: String)  {
        //TODO check internet connection
        networkConnectionInterceptor.isNetworkAvailable()

         viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val token = authService.sendLoginRequest(username, password)
                    if (token != null) {
                        userDataStorage.saveToken(token.token)
                        userDataStorage.saveEmail(username)
                    }
                    _loginResult.emit(true)
                    _loginAttempted.emit(true)
                } catch (e: Exception) {
                    _loginResult.emit(false)
                }
            }
         }

    }

    fun checkTokenValid(): Boolean {
        var result = false
        var token = Token("")

        viewModelScope.launch {
            val string = userDataStorage.getToken.first()
            if (string != null) {
                Log.i("token123", string)
            }
            if (string != null && string != "")
                token = Token(string)
            if (userDataStorage.isKeyStored(UserDataStorage.TOKEN).first()) {
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

    private val _signUpResult = MutableStateFlow<Boolean>(false)
    val signUpResult: StateFlow<Boolean> = _signUpResult

    private val _signUpAttempted = MutableStateFlow<Boolean>(false)
    val signUpAttempted: StateFlow<Boolean> = _signUpAttempted


    fun signUp(request : SignUpRequest)  {
        //TODO check internet connection
        networkConnectionInterceptor.isNetworkAvailable()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val token = authService.sendSignUpRequest(request)
                    if (token != null) {
                        userDataStorage.saveToken(token.token)
                        userDataStorage.saveEmail(request.email)
                    }
                    _signUpResult.emit(true)
                    _signUpAttempted.emit(true)
                } catch (e: Exception) {
                    _signUpResult.emit(false)
                }
            }
        }

    }

}