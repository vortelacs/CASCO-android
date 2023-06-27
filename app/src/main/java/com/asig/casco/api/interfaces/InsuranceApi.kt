package com.asig.casco.api.interfaces

import com.asig.casco.model.EmailRequest
import com.asig.casco.model.Insurance
import com.asig.casco.model.Insurer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

public interface InsuranceApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("insurance/{id}")
    suspend fun getInsuracnceByID(@Header("Authorization") accessToken: String, @Path("id") id: String): Insurance


    @POST("insurance/getByUserEmail")
    suspend fun getInsurancesByUserEmail(@Header("Authorization") token: String, @Body emailRequest: EmailRequest): ArrayList<Insurance>
    @POST("insurance/saveInsurance")
    suspend fun saveInsurance(@Header("Authorization") token: String, @Body insurance : Insurance): Insurance
}