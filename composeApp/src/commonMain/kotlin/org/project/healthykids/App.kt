package org.project.healthykids

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.natighajiyev.common.colors.PrimaryColors.BackgroundColor
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.project.healthykids.navigation.AppNavGraph


@Composable
@Preview
fun App() {

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundColor
        ) {
            AppNavGraph()
        }
    }
}