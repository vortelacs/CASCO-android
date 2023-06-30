package com.asig.casco.api.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.InsuranceApi
import com.asig.casco.model.EmailRequest
import com.asig.casco.model.Insurance
import com.asig.casco.model.Person
import com.asig.casco.model.Vehicle
import com.asig.casco.security.UserDataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsuranceViewModel @Inject constructor(
    private val retrofitFactory: RetrofitFactory,
    private val userDataStorage :  UserDataStorage
    ) : ViewModel()
{


    private val retrofit = retrofitFactory.getRetrofitInstance()
    private val insuranceApi: InsuranceApi = retrofit.create(InsuranceApi::class.java)

    private val _getInsurancesResult = MutableStateFlow(ArrayList<Insurance>())
    val getInsurancesResult: StateFlow<ArrayList<Insurance>> = _getInsurancesResult

    fun getInsuranceByEmail(email : String){
         viewModelScope.launch {
             val token = userDataStorage.getToken.firstOrNull()
             if (token != null) {
                 try {
                     val insurances = insuranceApi.getInsurancesByUserEmail("Bearer $token", EmailRequest(email))
                     Log.i("insurances", insurances.toString())
                     _getInsurancesResult.emit(insurances)
                 } catch (e: Exception) {
                     Log.i("insurance error", e.message.toString())
                     _getInsurancesResult.emit(ArrayList())
                 }
             }
         }
    }

    private val _postInsuranceResult = MutableStateFlow<Insurance>(Insurance(Vehicle(), ArrayList<Person>()))
    val postInsuranceResult: StateFlow<Insurance> = _postInsuranceResult

    fun saveInsurance(insuranceData: Insurance) {
        viewModelScope.launch {
            val token = userDataStorage.getToken.firstOrNull()
            insuranceData.user = userDataStorage.getEmail.first().toString()
            if (token != null) {
                try {
                    val savedInsurance = insuranceApi.saveInsurance("Bearer $token", insuranceData)
                    _postInsuranceResult.emit(savedInsurance)
                } catch (e: Exception) {
                    Log.i("insurance error", e.message.toString())
                }
            }
        }
    }

}