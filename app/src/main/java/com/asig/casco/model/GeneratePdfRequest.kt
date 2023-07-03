package com.asig.casco.model

data class GeneratePdfRequest(
    val user: String,
    val date: String,
    val price: String
)
