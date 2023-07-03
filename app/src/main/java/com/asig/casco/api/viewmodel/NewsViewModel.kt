package com.asig.casco.api.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.NewsApi
import com.asig.casco.model.News
import com.asig.casco.security.UserDataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    retrofitFactory: RetrofitFactory,
    private val tokenStorage :  UserDataStorage
) : ViewModel()

{


    private val retrofit = retrofitFactory.getRetrofitInstance()
    private val newsService: NewsApi = retrofit.create(NewsApi::class.java)

    private val _newsResult = MutableStateFlow<ArrayList<News>>(ArrayList<News>())
    val newsResult: StateFlow<ArrayList<News>> = _newsResult

    fun getAllNews(){
        viewModelScope.launch {
            val token = tokenStorage.getToken.firstOrNull()
            if (token != null) {
                try {
                    val newsList = newsService.getAllNews("Bearer $token")
                    Log.i("news", newsList.toString())
                    _newsResult.emit(newsList)
                } catch (e: Exception) {
                    Log.i("news error", e.message.toString())
                    _newsResult.emit(ArrayList())
                }
            }
        }
    }

}