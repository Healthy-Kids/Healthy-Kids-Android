package org.project.healthykids.models

import org.jetbrains.compose.resources.DrawableResource

data class WalkthroughModel(
    val imageId: DrawableResource,
    val title: String,
    val desc: String,
)
