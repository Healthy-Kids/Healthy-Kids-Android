package com.natighajiyev.common.typography

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

fun pixelToDp(pixel: Int, density: Density): Dp {
    return with(density){ pixel.toDp() }
}

fun pixelToSp(pixel: Int, density: Density): TextUnit {
    return with(density){ pixel.toSp() }
}