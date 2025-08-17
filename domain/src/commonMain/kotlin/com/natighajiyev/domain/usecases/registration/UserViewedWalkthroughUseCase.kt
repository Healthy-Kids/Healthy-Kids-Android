package com.natighajiyev.domain.usecases.registration

import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.flow

class UserViewedWalkthroughUseCase(
    private val repository: RegistrationRepository,
) {

    operator fun invoke(isViewed: Boolean = true) = flow {
        try {
            repository.userViewedWalkthrough(isViewed)
            emit(UiResult.Success(data = 1))
        } catch (e: Exception) {
            emit(UiResult.Error(responseCode = 400, message = e.message))
        }
    }
}