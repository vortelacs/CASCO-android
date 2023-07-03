package com.asig.casco.api.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.PDFApi
import com.asig.casco.model.GeneratePdfRequest
import com.asig.casco.security.UserDataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PDFViewModel @Inject constructor(
    retrofitFactory: RetrofitFactory,
    private val userDataStorage : UserDataStorage
) : ViewModel() {

    private val retrofit = retrofitFactory.getRetrofitInstance()
    private val pdfService: PDFApi = retrofit.create(PDFApi::class.java)

    private val _pdfResult = MutableStateFlow<String>("")
    val pdfResult: StateFlow<String> = _pdfResult

    fun getPDF(
        date : String,
        price : Float
    ){
        viewModelScope.launch {
            val token = userDataStorage.getToken.firstOrNull()
            val user = userDataStorage.getEmail.firstOrNull()
            if (token != null && user != null) {
                val request = GeneratePdfRequest(user, date, price.toString())
                try {
                    val url = pdfService.getPDFFile("Bearer $token", request)
                    _pdfResult.emit(url.url)
                    Log.i("pdf", url.url)
                } catch (e: Exception) {
                    Log.i("pdf error", e.message.toString())
                    _pdfResult.emit("error")
                }
            }
        }
    }
}