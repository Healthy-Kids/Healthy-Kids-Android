package com.natighajiyev.domain.usecases.profile

import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.flow

class GetAllergiesNameUseCase(private val repository: ProfileRepository) {

    operator fun invoke() = flow {
        val response = repository.getAllergiesNameList()

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