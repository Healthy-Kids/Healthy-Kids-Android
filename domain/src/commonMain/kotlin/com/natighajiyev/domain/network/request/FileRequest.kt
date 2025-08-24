package com.natighajiyev.domain.network.request

data class FileRequest(
    val title: String,
    val fileByteArray: ByteArray,
    val size: Long? = null
)
