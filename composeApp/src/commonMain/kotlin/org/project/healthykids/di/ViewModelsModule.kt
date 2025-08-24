package org.project.healthykids.di

import org.koin.dsl.module
import org.project.healthykids.screens.main.home.contract.HomeViewModel
import org.project.healthykids.screens.onboarding.contract.OnboardingViewModel
import org.project.healthykids.screens.registrations.contract.RegistrationViewModel

val viewModelsModule = module {
    single {
        OnboardingViewModel(
            get(),
            get(),
            get()
        )
    }

    single {
        RegistrationViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    single {
        HomeViewModel(
            get()
        )
    }
}