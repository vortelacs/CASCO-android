package com.asig.casco.api.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.PartnerApi
import com.asig.casco.model.Partner
import com.asig.casco.security.UserDataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnerViewModel @Inject constructor(
    private val retrofitFactory: RetrofitFactory,
    private val tokenStorage : UserDataStorage
) : ViewModel()

{


    private val retrofit = retrofitFactory.getRetrofitInstance()
    private val partnerService: PartnerApi = retrofit.create(PartnerApi::class.java)

    private val _partnerResult = MutableStateFlow<ArrayList<Partner>>(ArrayList<Partner>())
    val partnerResult: StateFlow<ArrayList<Partner>> = _partnerResult

    fun getAllPartner(){
        viewModelScope.launch {
            val token = tokenStorage.getToken.firstOrNull()
            if (token != null) {
                try {
                    val partnerList = partnerService.getAllPartners("Bearer $token")
                    Log.i("partner", partnerList.toString())
                    _partnerResult.emit(partnerList)
                } catch (e: Exception) {
                    Log.i("partner error", e.message.toString())
                    _partnerResult.emit(ArrayList())
                }
            }
        }
    }

}