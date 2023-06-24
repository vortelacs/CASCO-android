package com.asig.casco.api.interfaces

import com.asig.casco.model.Tariff
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


public interface TariffApi {


    @GET("insurer/{id}")
    abstract fun getTariffRate(@Header("Authorization") accessToken: String, @Path("id") id: String): Int

    interface ApiService {
        @POST("/calculate")
        suspend fun calculateTariff(@Body tariffDTO: Tariff): Response<Double>
    }



}