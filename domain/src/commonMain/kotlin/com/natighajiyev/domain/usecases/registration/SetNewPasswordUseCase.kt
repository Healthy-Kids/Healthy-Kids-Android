package com.natighajiyev.domain.usecases.registration

import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.flow

class SetNewPasswordUseCase(
    private val repository: RegistrationRepository
) {

    operator fun invoke(request: String) = flow {
        val response = repository.setNewPassword(request)
        emit(UiResult.Loading)

        when {
            response.data == null || response.result.resultCode in 400 until 500 ->
                emit(
                    UiResult.Error(
                        responseCode = response.result.resultCode,
                        message = response.result.message
                    )
                )

            else ->
                emit(UiResult.Success(data = response.data))
        }
    }
}