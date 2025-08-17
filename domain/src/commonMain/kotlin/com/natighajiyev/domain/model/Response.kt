package com.natighajiyev.domain.model

data class Response<T : Any>(
    val data: T?,
    val result: CrudModel
)
