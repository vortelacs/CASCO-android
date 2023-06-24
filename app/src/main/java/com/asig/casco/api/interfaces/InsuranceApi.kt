package com.asig.casco.api.interfaces

import com.asig.casco.model.Insurer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

public interface InsuranceApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("insurance/{id}")
    abstract fun getInsuracnceByID(@Header("Authorization") accessToken: String, @Path("id") id: String): Call<Insurer?>?

    @GET("insuranceByUser")
    abstract fun getInsurancesByUserId(@Header("Authorization") accessToken: String): Call<ArrayList<Insurer>>
}