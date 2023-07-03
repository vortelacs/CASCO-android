package com.asig.casco.model

data class SignUpRequest(
    var email : String,
    var password : String,
    val firstName: String = "",
    val lastName: String = "",
    val idnp: String = "",
    val phone: String = ""
)
