package org.project.healthykids

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.natighajiyev.common.colors.PrimaryColors.BackgroundColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.bottomVector
import healthykids.composeapp.generated.resources.compose_multiplatform
import healthykids.composeapp.generated.resources.ic_walkthrough_1
import healthykids.composeapp.generated.resources.ic_walkthrough_2
import healthykids.composeapp.generated.resources.ic_walkthrough_3
import healthykids.composeapp.generated.resources.topVector
import org.project.healthykids.models.WalkthroughModel
import org.project.healthykids.screens.RegistrationScreen
import org.project.healthykids.screens.WalkthroughScreen
import org.project.healthykids.screens.LoginScreen
import org.project.healthykids.screens.ForgotPasswordScreen
import org.project.healthykids.screens.OtpScreen
import org.project.healthykids.screens.WelcomeScreen


// Basit ekran router
sealed class Screen {
    data object Walkthrough : Screen()
    data object Register : Screen()
    data object Login : Screen()
    data object ForgotPassword : Screen()
    data object Otp : Screen()

    data object Welcome : Screen()
}

@Composable
@Preview
fun App() {

    MaterialTheme {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = BackgroundColor){

        var screen by remember { mutableStateOf<Screen>(Screen.Walkthrough) }



        when (screen) {
            Screen.Walkthrough -> WalkthroughScreen(
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
                ),
                onFinished = { screen = Screen.Register }
            )

            Screen.Register -> RegistrationScreen(
                onRegisterClick = { /*  logic */ },
                onLoginClick = { screen = Screen.Login }
            )

            Screen.Login -> LoginScreen(
                onLoginClick = { /* giriş  logic */ },
                onForgotPasswordClick = { screen = Screen.ForgotPassword }
            )

            Screen.ForgotPassword -> ForgotPasswordScreen(
                onSendClick = { screen = Screen.Otp
                },
            )
            Screen.Otp -> OtpScreen(
                onVerificationClick = { screen = Screen.Welcome
                },
            )
            Screen.Welcome -> WelcomeScreen {  }

        }
    }}
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