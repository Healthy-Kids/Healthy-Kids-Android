package org.project.healthykids.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import org.koin.mp.KoinPlatform.getKoin
import org.project.healthykids.common.MessageDisplayer
import org.project.healthykids.di.viewModelsModule
import org.project.healthykids.navigation.Navigation.OnboardingNav.*
import org.project.healthykids.navigation.Navigation.RegistrationNav.*
import org.project.healthykids.navigation.Navigation.HomeNav.*
import org.project.healthykids.screens.main.home.contract.HomeViewModel
import org.project.healthykids.screens.main.profile.AllergyScreen
import org.project.healthykids.screens.main.profile.ChildrenScreen
import org.project.healthykids.screens.main.profile.ProfileScreen
import org.project.healthykids.screens.main.profile.contract.ProfileViewModel
import org.project.healthykids.screens.onboarding.OnboardingScreen
import org.project.healthykids.screens.onboarding.WalkthroughScreen
import org.project.healthykids.screens.onboarding.contract.OnboardingViewModel
import org.project.healthykids.screens.registrations.ForgotPasswordScreen
import org.project.healthykids.screens.registrations.LoginScreen
import org.project.healthykids.screens.registrations.OtpScreen
import org.project.healthykids.screens.registrations.RegistrationScreen
import org.project.healthykids.screens.registrations.contract.RegistrationViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val messageDisplayer: MessageDisplayer = getKoin().get<MessageDisplayer>()
    val navState = remember { mutableIntStateOf(0) }


    NavHost(
        navController = navController,
        startDestination = Navigation.RegistrationNav.EntryPoint.tag
    ) {

        navigation(
            route = Navigation.OnboardingNav.EntryPoint.tag,
            startDestination = Onboarding.tag,
        ) {
            val viewModel: OnboardingViewModel = getKoin().get()

            composable(Onboarding.tag) {
                // TODO: Create Onboarding screen
                OnboardingScreen(navController = navController, viewModel = viewModel)
            }

            composable(Walkthrough.tag) {
                WalkthroughScreen(navController = navController, viewModel = viewModel)
            }
        }

        navigation(
            route = Navigation.RegistrationNav.EntryPoint.tag,
            startDestination = Login.tag,
        ) {
            val viewModel: RegistrationViewModel = getKoin().get()

            composable(Login.tag) {
                LoginScreen(
                    messageDisplayer = messageDisplayer,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(Register.tag) {
                RegistrationScreen(
                    messageDisplayer = messageDisplayer,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(ForgotPassword.tag) {
                ForgotPasswordScreen(
                    messageDisplayer = messageDisplayer,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(Otp.tag) {
                OtpScreen(
                    messageDisplayer = messageDisplayer,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }


        navigation(
            route = Navigation.HomeNav.EntryPoint.tag,
            startDestination = Welcome.tag,
        ) {
            val homeViewModel: HomeViewModel = getKoin().get()
            val profileViewModel: ProfileViewModel = getKoin().get()

            composable(Welcome.tag) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavBar(selectedIndex = navState)
                    }
                ) { scaffoldLayout ->
                    scaffoldLayout.calculateBottomPadding()

                    when (navState.intValue) {
                        0 -> {
                            // TODO: Healthy
                        }

                        1 -> {
                            val viewModel: HomeViewModel = getKoin().get()
                            // TODO: Home
                        }

                        2 -> {
                            // TODO: Profile
                        }

                    }
                }
            }


            composable(Allergy.tag) {
                AllergyScreen(viewModel = profileViewModel, navController = navController)
            }

            composable(Vaccine.tag) { }

            composable(Examination.tag) { }

            composable(Lang.tag) { }

            composable(PersonalData.tag) { }

            composable(Children.tag) {
                ChildrenScreen(
                    viewModel = profileViewModel,
                    navController = navController
                )
            }
        }
    }
}