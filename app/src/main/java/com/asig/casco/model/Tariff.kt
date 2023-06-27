package com.asig.casco.model

data class Tariff(
    var insurer: String,
    var insuranceType: String,
    var vehicleType: String,
    var age: Int,
    var isFranchise: Boolean
    )
