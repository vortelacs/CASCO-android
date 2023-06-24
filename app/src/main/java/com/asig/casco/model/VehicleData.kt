package com.asig.casco.model

data class VehicleData(
    val vehicleType: String,
    val make: String,
    val model: String,
    val year: Int,
    val price: Double,
    val currency: String,
    val certificateNumber: String,
    val registrationNumber: String,
    val wantFranchise: Boolean
)