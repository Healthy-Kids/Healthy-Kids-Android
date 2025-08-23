package org.project.healthykids.navigation

sealed interface Navigation {
    val tag: String

    open class HomeNav(override val tag: String = "HomeEntryPoint"): Navigation {
        data object EntryPoint: HomeNav()
        data object Welcome : HomeNav("Welcome")
        data object Profile : HomeNav("Profile")
        data object Examination : HomeNav("Examination")
        data object Allergy : HomeNav("Allergy")
        data object Vaccine : HomeNav("Vaccine")
        data object Lang : HomeNav("Lang")
        data object PersonalData : HomeNav("PersonalData")
        data object Children : HomeNav("Children")
    }

    open class RegistrationNav(override val tag: String = "RegisterEntryPoint"): Navigation {
        data object EntryPoint: RegistrationNav()
        data object Register : RegistrationNav("Register")
        data object Login : RegistrationNav("Login")
        data object ForgotPassword : RegistrationNav(" ForgotPassword")
        data object Otp : RegistrationNav("Otp")
    }

    open class OnboardingNav(override val tag: String = "OnboardingEntryPoint"): Navigation {
        data object EntryPoint: OnboardingNav()
        data object Onboarding : OnboardingNav("Onboarding")
        data object Walkthrough : OnboardingNav("Walkthrough")
    }
}
