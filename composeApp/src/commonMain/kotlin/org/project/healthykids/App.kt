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
import org.project.healthykids.navigation.AppNavGraph
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