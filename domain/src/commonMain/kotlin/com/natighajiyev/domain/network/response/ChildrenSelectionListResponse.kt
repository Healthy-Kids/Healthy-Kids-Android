package com.natighajiyev.domain.network.response

data class ChildrenSelectionListResponse(
    val children: List<ChildSelectionListResponse>
)

data class ChildSelectionListResponse(
    val id: String,
    val name: String,
    val image: String
)
