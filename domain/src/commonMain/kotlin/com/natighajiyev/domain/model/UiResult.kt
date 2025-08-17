package com.natighajiyev.domain.model

sealed class UiResult<T: Any> {
    data class Success<T : Any>(val data: T) : UiResult<T>()
    data class Error<T : Any>(val responseCode: Int, val message: String?) : UiResult<T>()
    data object Loading: UiResult<Nothing>()
}