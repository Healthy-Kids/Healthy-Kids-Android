package org.project.healthykids

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.compose_multiplatform
import healthykids.composeapp.generated.resources.ic_walkthrough_1
import healthykids.composeapp.generated.resources.ic_walkthrough_2
import healthykids.composeapp.generated.resources.ic_walkthrough_3
import org.project.healthykids.models.WalkthroughModel
import org.project.healthykids.screens.WalkthroughScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        WalkthroughScreen(
            walkthroughList = listOf(
                WalkthroughModel(
                    imageId = Res.drawable.ic_walkthrough_1,
                    title = "Забота о спине - легко и весело",
                    desc = "Игры, советы и упражнения для здоровья вашей спины"
                ),
                WalkthroughModel(
                    imageId = Res.drawable.ic_walkthrough_2,
                    title = "Забота о спине - легко и весело",
                    desc = "Игры, советы и упражнения для здоровья вашей спины"
                ),
                WalkthroughModel(
                    imageId = Res.drawable.ic_walkthrough_3,
                    title = "Забота о спине - легко и весело",
                    desc = "Игры, советы и упражнения для здоровья вашей спины"
                )

            )
        )
    }
}

@Composable
fun TemplateApp() {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}