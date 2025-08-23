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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.natighajiyev.common.colors.PrimaryColors.BackgroundColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.compose_multiplatform
import org.project.healthykids.screens.registrations.RegistrationScreen
import org.project.healthykids.screens.onboarding.WalkthroughScreen
import org.project.healthykids.screens.registrations.LoginScreen
import org.project.healthykids.screens.registrations.ForgotPasswordScreen
import org.project.healthykids.screens.registrations.OtpScreen
import org.project.healthykids.screens.WelcomeScreen
import org.project.healthykids.screens.main.ProfileScreens.ProfileScreen
import org.project.healthykids.screens.main.ProfileScreens.ExaminationsScreen
import org.project.healthykids.screens.main.ProfileScreens.AllergyScreen
import org.project.healthykids.screens.main.ProfileScreens.LangScreen
import org.project.healthykids.screens.main.ProfileScreens.VaccineScreen
import org.project.healthykids.screens.main.ProfileScreens.PersonalDataScreen
import org.project.healthykids.screens.main.ProfileScreens.ChildsScreen


sealed class Screen {
    data object Walkthrough : Screen()
    data object Register : Screen()
    data object Login : Screen()
    data object ForgotPassword : Screen()
    data object Otp : Screen()
    data object Welcome : Screen()
    data object Profile : Screen()
    data object Examination : Screen()
    data object Allergy : Screen()
    data object Vaccine : Screen()
    data object Lang : Screen()
    data object  PersonalData : Screen()
    data object ChildsScreen : Screen()
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
            Screen.Welcome -> WelcomeScreen (
                onStartAppClick = {screen = Screen.Profile
                },
            )
            Screen.Profile -> ProfileScreen (
                onLogoutClick = {screen = Screen.Login},
                onTabClick = {},
                onMyChildrenClick = {screen = Screen.ChildsScreen},
                onVaccinesClick = {screen = Screen.Vaccine},
                onExaminationsClick = { screen = Screen.Examination},
                onAllergyClick= { screen = Screen.Allergy},
                onPersonalDataClick= {screen = Screen.PersonalData},
                onLanguagesClick= { screen = Screen.Lang},
            )
            Screen.Examination -> ExaminationsScreen(
                onTabClick = {},
        onAddClick = {},
        onItemClick ={}
            )
            Screen.Allergy -> AllergyScreen {}
            Screen.Vaccine -> VaccineScreen {  }
            Screen.Lang -> LangScreen() { }
            Screen.PersonalData-> PersonalDataScreen {}
            Screen.ChildsScreen -> ChildsScreen {}
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: App")
            }
        }
    }
}