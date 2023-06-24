package com.asig.casco.api.interfaces

import com.asig.casco.model.Insurer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

public interface InsurerApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("insurer/{id}")
    abstract fun getInsurerById(@Header("Authorization") accessToken: String, @Path("id") id: String): Insurer

    @GET("insurer")
    abstract fun getInsurers(@Header("Authorization") accessToken: String): ArrayList<Insurer>
}