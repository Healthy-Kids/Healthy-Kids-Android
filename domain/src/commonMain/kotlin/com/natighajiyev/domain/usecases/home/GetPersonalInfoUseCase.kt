package com.natighajiyev.domain.usecases.home

import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.repository.HomeRepository
import kotlinx.coroutines.flow.flow

class GetPersonalInfoUseCase(private val repository: HomeRepository) {

    operator fun invoke() = flow {
        val response = repository.getPersonalInfo()

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