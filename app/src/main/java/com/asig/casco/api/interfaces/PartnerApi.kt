package com.asig.casco.api.interfaces

import com.asig.casco.model.Partner
import retrofit2.http.GET
import retrofit2.http.Header

public interface PartnerApi {

    @GET("partners")
    suspend fun getAllPartners(@Header("Authorization") accessToken: String): ArrayList<Partner>
}