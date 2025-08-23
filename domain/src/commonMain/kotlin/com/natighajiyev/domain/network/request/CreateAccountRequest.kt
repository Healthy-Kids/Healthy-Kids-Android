package com.natighajiyev.domain.network.request

data class CreateAccountRequest(
    val fullname: String,
    val phone: String,
    val email: String,
    val password: String,
)
