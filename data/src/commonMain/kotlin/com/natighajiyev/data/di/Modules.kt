package com.natighajiyev.data.di

import com.natighajiyev.data.local.source.UserDataSource
import com.natighajiyev.data.network.interceptor.tokenInterceptor
import com.natighajiyev.data.network.manager.TokenManager
import com.natighajiyev.data.network.services.RegistrationService
import com.natighajiyev.data.network.services.TokenService
import com.natighajiyev.data.repository.HomeRepositoryImpl
import com.natighajiyev.data.repository.RegistrationRepositoryImpl
import com.natighajiyev.domain.repository.HomeRepository
import com.natighajiyev.domain.repository.RegistrationRepository
import com.natighajiyev.domain.usecases.home.GetPersonalInfoUseCase
import com.natighajiyev.domain.usecases.registration.CheckOtpUseCase
import com.natighajiyev.domain.usecases.registration.CreateAccountUseCase
import com.natighajiyev.domain.usecases.registration.GetUserInfoUseCase
import com.natighajiyev.domain.usecases.registration.HasUserViewedWalkthroughUseCase
import com.natighajiyev.domain.usecases.registration.LoginUseCase
import com.natighajiyev.domain.usecases.registration.ResetPasswordUseCase
import com.natighajiyev.domain.usecases.registration.SetNewPasswordUseCase
import com.natighajiyev.domain.usecases.registration.UserViewedWalkthroughUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module


val httpClient = module {

    // TokenManager (depends on DB)
    single { TokenManager(get()) }

    // Refresh-only HttpClient
    single(named("refreshClient")) {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    // TokenService (for refresh calls)
    single { TokenService(get(named("refreshClient"))) }

    // Main HttpClient with both ContentNegotiation and TokenInterceptor
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(tokenInterceptor(get(), get())) // TokenService, TokenManager
        }
    }
}

val localModules = module {
    single<UserDataSource> { UserDataSource(get()) }
}

val networkModules = module {
    single<RegistrationService> { RegistrationService(get()) }
}

val repository = module {
    single<RegistrationRepository> { RegistrationRepositoryImpl(get(), get()) }
    single<HomeRepository> { HomeRepositoryImpl( get()) }
}

val registrationUseCases = module {
    single { GetUserInfoUseCase(get()) }
    single { HasUserViewedWalkthroughUseCase(get()) }
    single { UserViewedWalkthroughUseCase(get()) }
    single { LoginUseCase(get()) }
    single { CreateAccountUseCase(get()) }
    single { ResetPasswordUseCase(get()) }
    single { CheckOtpUseCase(get()) }
    single { SetNewPasswordUseCase(get()) }
}

val homeUseCases = module {
    single { GetPersonalInfoUseCase(get()) }
}