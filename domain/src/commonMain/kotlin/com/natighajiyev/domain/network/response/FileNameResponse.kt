package com.natighajiyev.domain.network.response

data class ListFileNameResponse(
    val files: List<FileNameResponse>
)

data class FileNameResponse(
    val id: String,
    val name: String,
    val count: String,
    val type: String,
)