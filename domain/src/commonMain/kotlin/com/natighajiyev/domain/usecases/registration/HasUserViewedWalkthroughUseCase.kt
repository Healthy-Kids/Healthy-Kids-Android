package com.natighajiyev.domain.usecases.registration

import com.natighajiyev.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.flow

class HasUserViewedWalkthroughUseCase(
    private val repository: RegistrationRepository
) {

    operator fun invoke() = flow{
        emit(repository.hasUserViewedWalkthrough())
    }

}