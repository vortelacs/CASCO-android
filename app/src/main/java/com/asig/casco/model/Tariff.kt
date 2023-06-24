package com.asig.casco.model

data class Tariff(
    val insurer: String,
    val insuranceType: String,
    val vehicleType: String,
    val age: Int,
    val isFranchise: Boolean
    )
