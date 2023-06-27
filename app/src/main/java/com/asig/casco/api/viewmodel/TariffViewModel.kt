package com.asig.casco.api.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.TariffApi
import com.asig.casco.model.EmailRequest
import com.asig.casco.model.Tariff
import com.asig.casco.security.UserDataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TariffViewModel @Inject constructor(
    private val retrofitFactory: RetrofitFactory,
    private val tokenStorage : UserDataStorage
) : ViewModel()
{
    private val retrofit = retrofitFactory.getRetrofitInstance()
    private val tariffApi: TariffApi = retrofit.create(TariffApi::class.java)

    private val _getTariffResult = MutableStateFlow(0.0)
    val getTariffResult: StateFlow<Double> = _getTariffResult

    fun getTarriff(tariff : Tariff) {
        viewModelScope.launch {
            val token = tokenStorage.getToken.firstOrNull()
            if (token != null) {
                try {
                    val tariff = tariffApi.getPrice("Bearer $token", tariff)
                    Log.i("tariff", tariff.toString())
                    _getTariffResult.emit(tariff)
                } catch (e: Exception) {
                    Log.i("tariff error", e.message.toString())
                    _getTariffResult.emit(-1.0)
                }
            }
        }
    }
}