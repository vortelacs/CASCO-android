package com.asig.casco.model

data class Insurance(
    var vehicle : Vehicle,
    var persons : List<Person>,
    var insuranceType : String = "",
    var assurancePrice : String = "",
    var insurer : String = "",
    var countryBlock : String = "",
    var effectiveDate : String = "",
    var expireDate : String = "",

)
