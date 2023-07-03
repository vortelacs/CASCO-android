package com.asig.casco.api.interfaces

import com.asig.casco.model.GeneratePdfRequest
import com.asig.casco.model.PDFLink
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PDFApi {

    @POST("pdf/generate")
    suspend fun getPDFFile(@Header("Authorization") token: String, @Body request : GeneratePdfRequest): PDFLink
}