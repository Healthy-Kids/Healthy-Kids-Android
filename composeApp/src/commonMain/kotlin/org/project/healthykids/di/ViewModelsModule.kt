package org.project.healthykids.di

import org.koin.dsl.module
import org.project.healthykids.screens.onboarding.contract.OnboardingViewModel

val viewModelsModule = module {
    single {
        OnboardingViewModel(
            get(),
            get(),
            get()
        )
    }
}