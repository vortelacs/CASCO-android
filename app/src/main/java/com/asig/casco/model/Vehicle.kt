package com.asig.casco.model

data class Vehicle(
    var type: String = "",
    var model: String = "",
    var make: String= "",
    var year: Int = 0,
    var carPrice: Float = 0f,
    var certificateNumber: String = "",
    var registrationNumber: String = "",
)
