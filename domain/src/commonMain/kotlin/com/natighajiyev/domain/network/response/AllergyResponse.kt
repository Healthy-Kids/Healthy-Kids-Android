package com.natighajiyev.domain.network.response

data class ListAllergyResponse(
    val allergies: List<AllergyResponse>
)
data class AllergyResponse(
    val title: String,
    val description: String
)